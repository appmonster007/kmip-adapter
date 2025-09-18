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
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for KeyCompressionType.
 */
public class KeyCompressionTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyCompressionType> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_COMPRESSION_TYPE);

    @Override
    public KeyCompressionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "Expected XML element object for KeyCompressionType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyCompressionType.class, "Invalid Tag for KeyCompressionType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
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

        KeyCompressionType keycompressiontype = new KeyCompressionType(KeyCompressionType.fromName(spec, description));
        if (!keycompressiontype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("KeyCompressionType '%s' not supported for spec %s", description, spec));
        }

        return keycompressiontype;
    }
}
