package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.RequestMessageStructure;
import org.purpleBean.kmip.common.structure.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestMessage;

import java.io.IOException;

public class RequestMessageXmlDeserializer extends KmipDataTypeXmlDeserializer<RequestMessageStructure> {

    @Override
    public RequestMessageStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        SimpleRequestMessage simpleRequestMessage = ctxt.readValue(p, SimpleRequestMessage.class);

        ProtocolVersion protocolVersion = simpleRequestMessage.getRequestHeader().getProtocolVersion();
        KmipSpec previous = KmipContext.getSpec();
        KmipSpec spec = KmipSpec.fromValue(protocolVersion);
        KmipContext.setSpec(spec);
        try {
            return deserializeByProtocolVersion(p, simpleRequestMessage, protocolVersion);
        } finally {
            if (previous != null) {
                KmipContext.setSpec(previous);
            } else {
                KmipContext.clear();
            }
        }
    }

    private RequestMessageStructure deserializeByProtocolVersion(
            JsonParser p,
            SimpleRequestMessage simpleRequestMessage,
            ProtocolVersion protocolVersion
    ) throws IOException {
        return switch (protocolVersion.toString()) {
            default -> simpleRequestMessage;
        };
    }
}
