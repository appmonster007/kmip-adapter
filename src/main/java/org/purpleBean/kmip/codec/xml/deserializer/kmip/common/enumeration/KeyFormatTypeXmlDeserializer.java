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
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for KeyFormatType.
 */
public class KeyFormatTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyFormatType> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_FORMAT_TYPE);

    @Override
    public KeyFormatType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyFormatType.class, "Expected XML element object for KeyFormatType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyFormatType.class, "Invalid Tag for KeyFormatType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyFormatType.class, "Missing or invalid '@type' attribute for KeyFormatType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyFormatType.class, "Missing or non-text '@value' attribute for KeyFormatType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        KeyFormatType keyformattype = new KeyFormatType(KeyFormatType.fromName(spec, description));
        if (!keyformattype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("KeyFormatType '%s' not supported for spec %s", description, spec));
        }

        return keyformattype;
    }
}
