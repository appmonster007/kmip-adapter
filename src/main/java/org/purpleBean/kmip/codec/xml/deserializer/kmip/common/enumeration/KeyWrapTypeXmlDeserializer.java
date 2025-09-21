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
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for KeyWrapType.
 */
public class KeyWrapTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<KeyWrapType> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_WRAP_TYPE);

    @Override
    public KeyWrapType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(KeyWrapType.class, "Expected XML element object for KeyWrapType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(KeyWrapType.class, "Invalid Tag for KeyWrapType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(KeyWrapType.class, "Missing or invalid '@type' attribute for KeyWrapType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(KeyWrapType.class, "Missing or non-text '@value' attribute for KeyWrapType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        KeyWrapType keywraptype = new KeyWrapType(KeyWrapType.fromName(spec, description));
        if (!keywraptype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("KeyWrapType '%s' not supported for spec %s", description, spec));
        }

        return keywraptype;
    }
}
