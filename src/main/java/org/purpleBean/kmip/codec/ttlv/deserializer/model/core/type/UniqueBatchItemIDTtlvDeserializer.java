package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDTtlvDeserializer() {
        super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType, ByteBuffer.class, value -> UniqueBatchItemID.builder().value(value).build());
    }
}