package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.NameType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for NameType.
 */
public class NameTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<NameType> {

    @Override
    public NameType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(NameType.class, "Expected XML element object for NameType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(NameType.class, "Missing or invalid '@type' attribute for NameType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NameType.class, "Missing or non-text '@value' attribute for NameType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        NameType nametype = new NameType(NameType.fromName(spec, description));
        if (!nametype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("NameType '%s' not supported for spec %s", description, spec));
        }

        return nametype;
    }
}
