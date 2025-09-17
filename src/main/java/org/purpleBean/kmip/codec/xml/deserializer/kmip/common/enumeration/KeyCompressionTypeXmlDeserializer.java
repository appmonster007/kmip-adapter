package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

import java.io.IOException;
import java.util.NoSuchElementException;

public class KeyCompressionTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyCompressionType> {

    @Override
    public KeyCompressionType deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "Expected XML element object for KeyCompressionType");
            return null;
        }
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "Missing or invalid '@type' attribute for KeyCompressionType");
            return null;
        }
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "Missing or non-text '@value' attribute for KeyCompressionType");
            return null;
        }
        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();
        KeyCompressionType.Value v;
        try {
            v = KeyCompressionType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "Unknown value '" + description + "' for spec " + spec);
            return null;
        }
        KeyCompressionType result = new KeyCompressionType(v);
        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException("KeyCompressionType '" + description + "' not supported for spec " + spec);
        }
        return result;
    }
}
