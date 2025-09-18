package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ValidityIndicator.
 */
public class ValidityIndicatorXmlDeserializer extends KmipDataTypeXmlDeserializer<ValidityIndicator> {

    @Override
    public ValidityIndicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ValidityIndicator.class, "Expected XML element object for ValidityIndicator");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ValidityIndicator.class, "Missing or invalid '@type' attribute for ValidityIndicator");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ValidityIndicator.class, "Missing or non-text '@value' attribute for ValidityIndicator");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ValidityIndicator validityindicator = new ValidityIndicator(ValidityIndicator.fromName(spec, description));
        if (!validityindicator.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("ValidityIndicator '%s' not supported for spec %s", description, spec));
        }

        return validityindicator;
    }
}
