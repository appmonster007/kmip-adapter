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
import org.purpleBean.kmip.common.DestroyDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class DestroyDateXmlDeserializer extends KmipDataTypeXmlDeserializer<DestroyDate> {
    private final KmipTag kmipTag = DestroyDate.kmipTag;
    private final EncodingType encodingType = DestroyDate.encodingType;

    @Override
    public DestroyDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(DestroyDate.class, "Invalid Tag for DestroyDate");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        DestroyDate.DestroyDateBuilder builder = DestroyDate.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(DestroyDate.class, "Missing or invalid 'type' attribute for DestroyDate");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(DestroyDate.class,
                                "Missing or non-text 'value' for DestroyDate");
                        return null;
                    }
                    builder.value(OffsetDateTime.parse(p.getText()));
                }
            }
        }

        DestroyDate destroyDate = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!destroyDate.isSupported()) {
            ctxt.reportInputMismatch(DestroyDate.class, "DestroyDate not supported for spec " + spec);
            return null;
        }

        return destroyDate;
    }
}