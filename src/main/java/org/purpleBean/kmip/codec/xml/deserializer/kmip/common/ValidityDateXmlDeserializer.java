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
import org.purpleBean.kmip.common.ValidityDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ValidityDateXmlDeserializer extends KmipDataTypeXmlDeserializer<ValidityDate> {
    private final KmipTag kmipTag = ValidityDate.kmipTag;
    private final EncodingType encodingType = ValidityDate.encodingType;

    @Override
    public ValidityDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(ValidityDate.class, "Invalid Tag for ValidityDate");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        ValidityDate.ValidityDateBuilder builder = ValidityDate.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(ValidityDate.class, "Missing or invalid 'type' attribute for ValidityDate");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(ValidityDate.class,
                                "Missing or non-text 'value' for ValidityDate");
                        return null;
                    }
                    builder.value(OffsetDateTime.parse(p.getText()));
                }
            }
        }

        ValidityDate validityDate = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!validityDate.isSupported()) {
            ctxt.reportInputMismatch(ValidityDate.class, "ValidityDate not supported for spec " + spec);
            return null;
        }

        return validityDate;
    }
}