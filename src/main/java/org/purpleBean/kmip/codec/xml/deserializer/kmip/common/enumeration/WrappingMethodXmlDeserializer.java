package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for WrappingMethod.
 */
public class WrappingMethodXmlDeserializer extends KmipDataTypeXmlDeserializer<WrappingMethod> {

    @Override
    public WrappingMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(WrappingMethod.class, "Expected XML element object for WrappingMethod");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(WrappingMethod.class, "Missing or invalid '@type' attribute for WrappingMethod");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(WrappingMethod.class, "Missing or non-text '@value' attribute for WrappingMethod");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        WrappingMethod wrappingmethod = new WrappingMethod(WrappingMethod.fromName(spec, description));
        if (!wrappingmethod.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("WrappingMethod '%s' not supported for spec %s", description, spec));
        }

        return wrappingmethod;
    }
}
