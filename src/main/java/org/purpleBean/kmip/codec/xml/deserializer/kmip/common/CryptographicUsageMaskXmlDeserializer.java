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
import org.purpleBean.kmip.common.CryptographicUsageMask;

import java.io.IOException;

public class CryptographicUsageMaskXmlDeserializer extends KmipDataTypeXmlDeserializer<CryptographicUsageMask> {
    private final KmipTag kmipTag = CryptographicUsageMask.kmipTag;
    private final EncodingType encodingType = CryptographicUsageMask.encodingType;

    @Override
    public CryptographicUsageMask deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "Invalid Tag for CryptographicUsageMask");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CryptographicUsageMask.CryptographicUsageMaskBuilder builder = CryptographicUsageMask.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CryptographicUsageMask.class, "Missing or invalid 'type' attribute for CryptographicUsageMask");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CryptographicUsageMask.class,
                                "Missing or non-text 'value' for CryptographicUsageMask");
                        return null;
                    }
                    builder.value(Integer.valueOf(p.getText()));
                }
            }
        }

        CryptographicUsageMask cryptographicUsageMask = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicUsageMask.isSupported()) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "CryptographicUsageMask not supported for spec " + spec);
            return null;
        }

        return cryptographicUsageMask;
    }
}