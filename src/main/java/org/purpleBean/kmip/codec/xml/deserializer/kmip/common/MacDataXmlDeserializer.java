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
import org.purpleBean.kmip.common.MacData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MacDataXmlDeserializer extends KmipDataTypeXmlDeserializer<MacData> {
    private final KmipTag kmipTag = MacData.kmipTag;
    private final EncodingType encodingType = MacData.encodingType;

    @Override
    public MacData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MacData.class, "Expected XML object for MacData");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MacData.class, "Invalid Tag for MacData");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MacData.class, "Missing or invalid '@type' attribute for MacData");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MacData.class,
                    "Missing or non-text 'value' for MacData");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        MacData macData = MacData.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!macData.isSupported()) {
            ctxt.reportInputMismatch(MacData.class, "MacData not supported for spec " + spec);
            return null;
        }

        return macData;
    }
}
