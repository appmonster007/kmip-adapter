package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDJsonDeserializer() {
        super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType, ByteBuffer.class, value -> UniqueBatchItemID.builder().value(value).build());
    }
}