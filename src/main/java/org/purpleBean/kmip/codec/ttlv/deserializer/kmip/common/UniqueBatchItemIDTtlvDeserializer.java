package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDTtlvDeserializer extends AbstractKmipTtlvDeserializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDTtlvDeserializer() {
        super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType, ByteBuffer.class, value -> UniqueBatchItemID.builder().value(value).build());
    }
}