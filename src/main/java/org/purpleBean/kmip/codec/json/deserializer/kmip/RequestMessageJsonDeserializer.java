package org.purpleBean.kmip.codec.json.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class RequestMessageJsonDeserializer extends KmipDataTypeJsonDeserializer<RequestMessageStructure> {

    @Override
    public RequestMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        SimpleRequestMessage simpleRequestMessage = p.getCodec().treeToValue(node, SimpleRequestMessage.class);

        ProtocolVersion protocolVersion = simpleRequestMessage.getRequestHeader().getProtocolVersion();
        KmipSpec previous = KmipContext.getSpec();
        KmipSpec spec = KmipSpec.fromValue(protocolVersion);
        KmipContext.setSpec(spec);
        try {
            return deserializeByProtocolVersion(p, node, protocolVersion);
        } finally {
            if (previous != null) {
                KmipContext.setSpec(previous);
            } else {
                KmipContext.clear();
            }
        }
    }

    private RequestMessageStructure deserializeByProtocolVersion(JsonParser p, JsonNode node, ProtocolVersion protocolVersion) throws IOException {
        return switch (protocolVersion.toString()) {
            default -> p.getCodec().treeToValue(node, SimpleRequestMessage.class);
        };
    }
}
