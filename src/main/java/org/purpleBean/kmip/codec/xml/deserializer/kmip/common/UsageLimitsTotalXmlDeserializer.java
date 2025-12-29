package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.UsageLimitsTotal;

import java.io.IOException;

public class UsageLimitsTotalXmlDeserializer extends KmipDataTypeXmlDeserializer<UsageLimitsTotal> {
    private final KmipTag kmipTag = UsageLimitsTotal.kmipTag;
    private final EncodingType encodingType = UsageLimitsTotal.encodingType;

    @Override
    public UsageLimitsTotal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(UsageLimitsTotal.class, "Expected XML object for UsageLimitsTotal");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(UsageLimitsTotal.class, "Invalid Tag for UsageLimitsTotal");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(UsageLimitsTotal.class, "Missing or invalid '@type' attribute for UsageLimitsTotal");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UsageLimitsTotal.class,
                    "Missing or non-number 'value' for UsageLimitsTotal");
            return null;
        }

        Long value = codec.treeToValue(valueNode, Long.class);
        UsageLimitsTotal usageLimitsTotal = UsageLimitsTotal.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!usageLimitsTotal.isSupported()) {
            ctxt.reportInputMismatch(UsageLimitsTotal.class, "UsageLimitsTotal not supported for spec " + spec);
            return null;
        }

        return usageLimitsTotal;
    }
}