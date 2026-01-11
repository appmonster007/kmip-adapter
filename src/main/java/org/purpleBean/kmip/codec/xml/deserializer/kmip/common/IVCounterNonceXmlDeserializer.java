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
import org.purpleBean.kmip.common.IVCounterNonce;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IVCounterNonceXmlDeserializer extends KmipDataTypeXmlDeserializer<IVCounterNonce> {
    private final KmipTag kmipTag = IVCounterNonce.kmipTag;
    private final EncodingType encodingType = IVCounterNonce.encodingType;

    @Override
    public IVCounterNonce deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(IVCounterNonce.class, "Invalid Tag for IVCounterNonce");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        IVCounterNonce.IVCounterNonceBuilder builder = IVCounterNonce.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(IVCounterNonce.class, "Missing or invalid 'type' attribute for IVCounterNonce");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(IVCounterNonce.class,
                                "Missing or non-text 'value' for IVCounterNonce");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        IVCounterNonce iVCounterNonce = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!iVCounterNonce.isSupported()) {
            ctxt.reportInputMismatch(IVCounterNonce.class, "IVCounterNonce not supported for spec " + spec);
            return null;
        }

        return iVCounterNonce;
    }
}