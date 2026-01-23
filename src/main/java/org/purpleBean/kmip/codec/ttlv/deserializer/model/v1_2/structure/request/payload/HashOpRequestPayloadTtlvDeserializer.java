package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.HashOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HashOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<HashOpRequestPayload, HashOpRequestPayload.HashOpRequestPayloadBuilder> {

    public HashOpRequestPayloadTtlvDeserializer() {
        super(HashOpRequestPayload.kmipTag);
    }

    @Override
    protected HashOpRequestPayload.HashOpRequestPayloadBuilder createBuilder() {
        return HashOpRequestPayload.builder();
    }

    @Override
    protected void setValue(HashOpRequestPayload.HashOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected HashOpRequestPayload build(HashOpRequestPayload.HashOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return HashOpRequestPayload.encodingType;
    }
}
