package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

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
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OpaqueDataValueXmlDeserializer extends KmipDataTypeXmlDeserializer<OpaqueDataValue> {
    private final KmipTag kmipTag = OpaqueDataValue.kmipTag;
    private final EncodingType encodingType = OpaqueDataValue.encodingType;

    @Override
    public OpaqueDataValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(OpaqueDataValue.class, "Expected XML object for OpaqueDataValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(OpaqueDataValue.class, "Invalid Tag for OpaqueDataValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(OpaqueDataValue.class, "Missing or invalid '@type' attribute for OpaqueDataValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OpaqueDataValue.class,
                    "Missing or non-text 'value' for OpaqueDataValue");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        OpaqueDataValue opaqueDataValue = OpaqueDataValue.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!opaqueDataValue.isSupported()) {
            ctxt.reportInputMismatch(OpaqueDataValue.class, "OpaqueDataValue not supported for spec " + spec);
            return null;
        }

        return opaqueDataValue;
    }
}