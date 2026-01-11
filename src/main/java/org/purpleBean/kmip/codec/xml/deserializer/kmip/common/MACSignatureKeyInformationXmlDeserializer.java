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
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

import java.io.IOException;

public class MACSignatureKeyInformationXmlDeserializer extends KmipDataTypeXmlDeserializer<MACSignatureKeyInformation> {
    private final KmipTag kmipTag = MACSignatureKeyInformation.kmipTag;
    private final EncodingType encodingType = MACSignatureKeyInformation.encodingType;

    @Override
    public MACSignatureKeyInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "Invalid Tag for MACSignatureKeyInformation");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        MACSignatureKeyInformation.MACSignatureKeyInformationBuilder builder = MACSignatureKeyInformation.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "Missing or invalid 'type' attribute for MACSignatureKeyInformation");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(MACSignatureKeyInformation.class,
                                "Missing or non-text 'value' for MACSignatureKeyInformation");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        MACSignatureKeyInformation mACSignatureKeyInformation = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!mACSignatureKeyInformation.isSupported()) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "MACSignatureKeyInformation not supported for spec " + spec);
            return null;
        }

        return mACSignatureKeyInformation;
    }
}