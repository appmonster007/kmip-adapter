package org.purpleBean.kmip.codec.ttlv.deserializer.api.request;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.api.request.RequestMessageStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RequestMessageStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<RequestMessageStructure> {

    @Override
    public RequestMessageStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        SimpleRequestMessage simpleRequestMessage = mapper.readValue(ttlvBuffer, SimpleRequestMessage.class);
        ttlvBuffer.rewind();
        ProtocolVersion protocolVersion = simpleRequestMessage.getRequestHeader().getProtocolVersion();
        KmipSpec previous = KmipContext.getSpec();
        KmipSpec spec = KmipSpec.fromValue(protocolVersion);
        KmipContext.setSpec(spec);
        try {
            return deserializeByProtocolVersion(protocolVersion, ttlvBuffer, mapper);
        } finally {
            if (previous != null) {
                KmipContext.setSpec(previous);
            } else {
                KmipContext.clear();
            }
        }
    }

    private RequestMessageStructure deserializeByProtocolVersion(ProtocolVersion protocolVersion, ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        return switch (protocolVersion.toString()) {
            default -> mapper.readValue(ttlvBuffer, SimpleRequestMessage.class);
        };
    }
}
