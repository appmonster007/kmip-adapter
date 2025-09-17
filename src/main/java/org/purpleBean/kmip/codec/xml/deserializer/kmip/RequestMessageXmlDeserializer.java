package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.RequestMessageStructure;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class RequestMessageXmlDeserializer extends KmipDataTypeXmlDeserializer<RequestMessageStructure> {

    @Override
    public RequestMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        SimpleRequestMessage simpleRequestMessage = p.getCodec().treeToValue(node, SimpleRequestMessage.class);

        ProtocolVersion protocolVersion = simpleRequestMessage.getRequestHeader().getProtocolVersion();
        KmipContext.setSpec(KmipSpec.fromValue(protocolVersion));

        return deserializeByProtocolVersion(p, node, protocolVersion);
    }

    private RequestMessageStructure deserializeByProtocolVersion(JsonParser p, JsonNode node, ProtocolVersion protocolVersion) throws IOException {
        return switch (protocolVersion.toString()) {
            default -> p.getCodec().treeToValue(node, SimpleRequestMessage.class);
        };
    }
}
