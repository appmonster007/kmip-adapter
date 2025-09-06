package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;

public class ProtocolVersionMajorJsonDeserializer extends KmipDataTypeJsonDeserializer<ProtocolVersion.ProtocolVersionMajor> {
    @Override
    public ProtocolVersion.ProtocolVersionMajor deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();

        if (!node.isObject() || tag != KmipTag.Standard.PROTOCOL_VERSION_MAJOR) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMajor.class, "Expected object for ProtocolVersionMajor");
            return null;
        }
        JsonNode val = node.get("value");
        if (val == null || !val.isInt()) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMajor.class, "Missing or non-int 'value' for ProtocolVersionMajor");
            return null;
        }
        return ProtocolVersion.ProtocolVersionMajor.of(val.intValue());
    }
}
