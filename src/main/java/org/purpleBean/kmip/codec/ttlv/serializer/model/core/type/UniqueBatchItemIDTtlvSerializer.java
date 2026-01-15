package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDTtlvSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}