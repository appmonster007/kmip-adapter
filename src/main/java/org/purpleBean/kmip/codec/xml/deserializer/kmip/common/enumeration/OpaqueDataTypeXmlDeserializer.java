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
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for OpaqueDataType.
 */
public class OpaqueDataTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<OpaqueDataType> {
    private final KmipTag kmipTag = OpaqueDataType.kmipTag;
    private final EncodingType encodingType = OpaqueDataType.encodingType;

    @Override
    public OpaqueDataType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(OpaqueDataType.class, "Expected XML element object for OpaqueDataType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(OpaqueDataType.class, "Invalid Tag for OpaqueDataType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(OpaqueDataType.class, "Missing or invalid '@type' attribute for OpaqueDataType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OpaqueDataType.class, "Missing or non-text '@value' attribute for OpaqueDataType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        OpaqueDataType opaquedatatype = new OpaqueDataType(OpaqueDataType.fromName(description));
        if (!opaquedatatype.isSupported()) {
            throw new NoSuchElementException(
                String.format("OpaqueDataType '%s' not supported for spec %s", description, spec));
        }

        return opaquedatatype;
    }
}
