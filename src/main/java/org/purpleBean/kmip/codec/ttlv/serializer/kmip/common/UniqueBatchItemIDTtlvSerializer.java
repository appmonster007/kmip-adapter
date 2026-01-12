package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDTtlvSerializer extends AbstractKmipTtlvSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDTtlvSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}