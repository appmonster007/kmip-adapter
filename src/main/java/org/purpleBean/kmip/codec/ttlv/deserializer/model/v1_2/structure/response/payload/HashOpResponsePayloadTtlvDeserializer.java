package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.HashOpResponsePayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class HashOpResponsePayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<HashOpResponsePayload, HashOpResponsePayload.HashOpResponsePayloadBuilder> {

    public HashOpResponsePayloadTtlvDeserializer() {
        super(HashOpResponsePayload.kmipTag);
    }

    @Override
    protected HashOpResponsePayload.HashOpResponsePayloadBuilder createBuilder() {
        return HashOpResponsePayload.builder();
    }

    @Override
    protected void setValue(HashOpResponsePayload.HashOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.DATA)) {
            builder.data(mapper.readValue(p, DataByteString.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected HashOpResponsePayload build(HashOpResponsePayload.HashOpResponsePayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return HashOpResponsePayload.encodingType;
    }
}
