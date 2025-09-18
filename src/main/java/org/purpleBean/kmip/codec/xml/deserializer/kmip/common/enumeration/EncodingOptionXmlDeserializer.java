package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for EncodingOption.
 */
public class EncodingOptionXmlDeserializer extends KmipDataTypeXmlDeserializer<EncodingOption> {

    @Override
    public EncodingOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(EncodingOption.class, "Expected XML element object for EncodingOption");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(EncodingOption.class, "Missing or invalid '@type' attribute for EncodingOption");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(EncodingOption.class, "Missing or non-text '@value' attribute for EncodingOption");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        EncodingOption encodingoption = new EncodingOption(EncodingOption.fromName(spec, description));
        if (!encodingoption.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("EncodingOption '%s' not supported for spec %s", description, spec));
        }

        return encodingoption;
    }
}
