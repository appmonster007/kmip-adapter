package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UniqueBatchItemIDTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UniqueBatchItemID, UniqueBatchItemID.UniqueBatchItemIDBuilder> {

    public UniqueBatchItemIDTtlvDeserializer() {
        super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType);
    }

    @Override
    protected UniqueBatchItemID.UniqueBatchItemIDBuilder createBuilder() {
        return UniqueBatchItemID.builder();
    }

    @Override
    protected void setValue(UniqueBatchItemID.UniqueBatchItemIDBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected UniqueBatchItemID build(UniqueBatchItemID.UniqueBatchItemIDBuilder builder) {
        return builder.build();
    }
}
