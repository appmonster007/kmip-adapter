package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDJsonSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}