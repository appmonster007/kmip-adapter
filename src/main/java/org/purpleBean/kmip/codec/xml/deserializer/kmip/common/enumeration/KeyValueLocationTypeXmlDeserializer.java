package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

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
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for KeyValueLocationType.
 */
public class KeyValueLocationTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyValueLocationType> {
    private final KmipTag kmipTag = KeyValueLocationType.kmipTag;
    private final EncodingType encodingType = KeyValueLocationType.encodingType;

    @Override
    public KeyValueLocationType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, "Expected XML element object for KeyValueLocationType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, "Invalid Tag for KeyValueLocationType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, "Missing or invalid '@type' attribute for KeyValueLocationType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyValueLocationType.class, "Missing or non-text '@value' attribute for KeyValueLocationType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        KeyValueLocationType keyvaluelocationtype = new KeyValueLocationType(KeyValueLocationType.fromName(description));
        if (!keyvaluelocationtype.isSupported()) {
            throw new NoSuchElementException(
                String.format("KeyValueLocationType '%s' not supported for spec %s", description, spec));
        }

        return keyvaluelocationtype;
    }
}
