package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;

import java.io.IOException;
import java.util.Map;

public class UsageLimitsXmlDeserializer extends KmipDataTypeXmlDeserializer<UsageLimits> {
    private final KmipTag kmipTag = UsageLimits.kmipTag;
    private final EncodingType encodingType = UsageLimits.encodingType;

    @Override
    public UsageLimits deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(UsageLimits.class, "Expected XML object for UsageLimits");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(UsageLimits.class, "Invalid Tag for UsageLimits");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        UsageLimits.UsageLimitsBuilder builder = UsageLimits.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        UsageLimits usageLimits = builder.build();

        if (!usageLimits.isSupported()) {
            ctxt.reportInputMismatch(UsageLimits.class, "UsageLimits not supported for spec " + spec);
            return null;
        }

        return usageLimits;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the XML node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            UsageLimits.UsageLimitsBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS_TOTAL -> builder.usageLimitsTotal(p.getCodec().treeToValue(node, UsageLimitsTotal.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT -> builder.usageLimitsCount(p.getCodec().treeToValue(node, UsageLimitsCount.class));
            case KmipTag.Standard.USAGE_LIMITS_UNIT -> builder.usageLimitsUnit(p.getCodec().treeToValue(node, UsageLimitsUnit.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
