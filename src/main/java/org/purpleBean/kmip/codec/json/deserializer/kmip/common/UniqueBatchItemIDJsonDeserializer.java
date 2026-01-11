package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDJsonDeserializer extends AbstractKmipJsonDeserializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDJsonDeserializer() {
        super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType, ByteBuffer.class, value -> UniqueBatchItemID.builder().value(value).build());
    }
}