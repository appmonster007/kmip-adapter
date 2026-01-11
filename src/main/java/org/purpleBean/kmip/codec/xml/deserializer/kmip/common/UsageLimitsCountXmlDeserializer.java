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
import org.purpleBean.kmip.common.UsageLimitsCount;

import java.io.IOException;

public class UsageLimitsCountXmlDeserializer extends KmipDataTypeXmlDeserializer<UsageLimitsCount> {
    private final KmipTag kmipTag = UsageLimitsCount.kmipTag;
    private final EncodingType encodingType = UsageLimitsCount.encodingType;

    @Override
    public UsageLimitsCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(UsageLimitsCount.class, "Invalid Tag for UsageLimitsCount");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        UsageLimitsCount.UsageLimitsCountBuilder builder = UsageLimitsCount.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(UsageLimitsCount.class, "Missing or invalid 'type' attribute for UsageLimitsCount");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(UsageLimitsCount.class,
                                "Missing or non-number 'value' for UsageLimitsCount");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, Long.class));
                }
            }
        }

        UsageLimitsCount usageLimitsCount = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!usageLimitsCount.isSupported()) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, "UsageLimitsCount not supported for spec " + spec);
            return null;
        }

        return usageLimitsCount;
    }
}