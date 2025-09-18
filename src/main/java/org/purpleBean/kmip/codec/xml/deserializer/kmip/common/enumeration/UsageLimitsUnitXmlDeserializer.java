package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for UsageLimitsUnit.
 */
public class UsageLimitsUnitXmlDeserializer extends KmipDataTypeXmlDeserializer<UsageLimitsUnit> {

    @Override
    public UsageLimitsUnit deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, "Expected XML element object for UsageLimitsUnit");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, "Missing or invalid '@type' attribute for UsageLimitsUnit");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, "Missing or non-text '@value' attribute for UsageLimitsUnit");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        UsageLimitsUnit usagelimitsunit = new UsageLimitsUnit(UsageLimitsUnit.fromName(spec, description));
        if (!usagelimitsunit.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("UsageLimitsUnit '%s' not supported for spec %s", description, spec));
        }

        return usagelimitsunit;
    }
}
