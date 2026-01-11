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
import org.purpleBean.kmip.common.FixedFieldLength;

import java.io.IOException;

public class FixedFieldLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<FixedFieldLength> {
    private final KmipTag kmipTag = FixedFieldLength.kmipTag;
    private final EncodingType encodingType = FixedFieldLength.encodingType;

    @Override
    public FixedFieldLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(FixedFieldLength.class, "Invalid Tag for FixedFieldLength");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        FixedFieldLength.FixedFieldLengthBuilder builder = FixedFieldLength.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(FixedFieldLength.class, "Missing or invalid 'type' attribute for FixedFieldLength");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(FixedFieldLength.class,
                                "Missing or non-numeric 'value' for FixedFieldLength");
                        return null;
                    }
                    builder.value(Integer.valueOf(p.getText()));
                }
            }
        }

        FixedFieldLength fixedFieldLength = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!fixedFieldLength.isSupported()) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "FixedFieldLength not supported for spec " + spec);
            return null;
        }

        return fixedFieldLength;
    }
}