package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;

public class ProtocolVersionMinorJsonDeserializer extends KmipDataTypeJsonDeserializer<ProtocolVersion.ProtocolVersionMinor> {
    @Override
    public ProtocolVersion.ProtocolVersionMinor deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();

        if (!node.isObject() || tag != KmipTag.Standard.PROTOCOL_VERSION_MINOR) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class, "Expected object for ProtocolVersionMinor");
            return null;
        }
        JsonNode val = node.get("value");
        if (val == null || !val.isInt()) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class, "Missing or non-int 'value' for ProtocolVersionMinor");
            return null;
        }
        return ProtocolVersion.ProtocolVersionMinor.of(val.intValue());
    }
}
