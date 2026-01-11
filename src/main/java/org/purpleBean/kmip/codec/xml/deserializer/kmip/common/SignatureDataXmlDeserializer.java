package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.SignatureData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SignatureDataXmlDeserializer extends KmipDataTypeXmlDeserializer<SignatureData> {
    private final KmipTag kmipTag = SignatureData.kmipTag;
    private final EncodingType encodingType = SignatureData.encodingType;

    @Override
    public SignatureData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(SignatureData.class, "Invalid Tag for SignatureData");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        SignatureData.SignatureDataBuilder builder = SignatureData.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(SignatureData.class, "Missing or invalid 'type' attribute for SignatureData");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(SignatureData.class,
                                "Missing or non-text 'value' for SignatureData");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        SignatureData signatureData = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!signatureData.isSupported()) {
            ctxt.reportInputMismatch(SignatureData.class, "SignatureData not supported for spec " + spec);
            return null;
        }

        return signatureData;
    }
}