package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for KeyRoleType.
 */
public class KeyRoleTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyRoleType> {

    @Override
    public KeyRoleType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyRoleType.class, "Expected XML element object for KeyRoleType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyRoleType.class, "Missing or invalid '@type' attribute for KeyRoleType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyRoleType.class, "Missing or non-text '@value' attribute for KeyRoleType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        KeyRoleType keyroletype = new KeyRoleType(KeyRoleType.fromName(spec, description));
        if (!keyroletype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("KeyRoleType '%s' not supported for spec %s", description, spec));
        }

        return keyroletype;
    }
}
