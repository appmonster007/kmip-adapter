package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDJsonSerializer extends AbstractKmipJsonSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDJsonSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}