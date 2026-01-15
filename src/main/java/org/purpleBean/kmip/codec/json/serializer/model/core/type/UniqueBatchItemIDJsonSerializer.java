package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDJsonSerializer extends AbstractKmipDataTypeJsonSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDJsonSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}