package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;

import java.io.IOException;

public class UsageLimitsXmlDeserializer extends KmipDataTypeXmlDeserializer<UsageLimits> {
    private final KmipTag kmipTag = UsageLimits.kmipTag;

    @Override
    public UsageLimits deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(UsageLimits.class, "Invalid Tag for UsageLimits");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        UsageLimits.UsageLimitsBuilder builder = UsageLimits.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(UsageLimits.class, "Unexpected token: " + p.currentToken());
            }
        }

        UsageLimits usageLimits = builder.build();

        if (!usageLimits.isSupported()) {
            ctxt.reportInputMismatch(UsageLimits.class, "UsageLimits not supported for spec " + spec);
            return null;
        }

        return usageLimits;
    }

    private void setValue(
            UsageLimits.UsageLimitsBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS_TOTAL ->
                    builder.usageLimitsTotal(ctxt.readValue(p, UsageLimitsTotal.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(ctxt.readValue(p, UsageLimitsCount.class));
            case KmipTag.Standard.USAGE_LIMITS_UNIT ->
                    builder.usageLimitsUnit(ctxt.readValue(p, UsageLimitsUnit.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}