package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDTtlvSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}