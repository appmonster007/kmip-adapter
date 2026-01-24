package org.purpleBean.kmip.codec.ttlv.deserializer.api.response;

import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseMessageStructure;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseMessage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseMessageStructureTtlvDeserializer extends KmipDataTypeTtlvDeserializer<ResponseMessageStructure> {

    @Override
    public ResponseMessageStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        SimpleResponseMessage simpleResponseMessage = mapper.readValue(ttlvBuffer, SimpleResponseMessage.class);
        ttlvBuffer.rewind();
        ProtocolVersion protocolVersion = simpleResponseMessage.getResponseHeader().getProtocolVersion();
        KmipSpec previous = KmipContext.getSpec();
        KmipSpec spec = KmipSpec.fromValue(protocolVersion);
        KmipContext.setSpec(spec);
        try {
            return super.deserialize(ttlvBuffer, mapper);
        } finally {
            if (previous != null) {
                KmipContext.setSpec(previous);
            } else {
                KmipContext.clear();
            }
        }
    }

    @Override
    public Class<? extends KmipDataType> getKmipDataTypeClass(KmipTag.Value kmipTag, EncodingType encodingType, TtlvMapper mapper) {
        return ResponseMessageStructure.getClassFromRegistry();
    }
}
