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
import org.purpleBean.kmip.common.UsageLimitsCount;

import java.io.IOException;

public class UsageLimitsCountXmlDeserializer extends KmipDataTypeXmlDeserializer<UsageLimitsCount> {
    private final KmipTag kmipTag = UsageLimitsCount.kmipTag;
    private final EncodingType encodingType = UsageLimitsCount.encodingType;

    @Override
    public UsageLimitsCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, "Expected XML object for UsageLimitsCount");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, "Invalid Tag for UsageLimitsCount");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, "Missing or invalid '@type' attribute for UsageLimitsCount");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UsageLimitsCount.class,
                    "Missing or non-number 'value' for UsageLimitsCount");
            return null;
        }

        Long value = codec.treeToValue(valueNode, Long.class);
        UsageLimitsCount usageLimitsCount = UsageLimitsCount.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!usageLimitsCount.isSupported()) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, "UsageLimitsCount not supported for spec " + spec);
            return null;
        }

        return usageLimitsCount;
    }
}