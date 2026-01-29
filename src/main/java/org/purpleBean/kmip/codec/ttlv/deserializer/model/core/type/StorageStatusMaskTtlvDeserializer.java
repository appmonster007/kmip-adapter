package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

import java.io.IOException;
import java.nio.ByteBuffer;

public class StorageStatusMaskTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<StorageStatusMask, StorageStatusMask.StorageStatusMaskBuilder> {

    public StorageStatusMaskTtlvDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType);
    }

    @Override
    protected StorageStatusMask.StorageStatusMaskBuilder createBuilder() {
        return StorageStatusMask.builder();
    }

    @Override
    protected void setValue(StorageStatusMask.StorageStatusMaskBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, Integer.class));
    }

    @Override
    protected StorageStatusMask build(StorageStatusMask.StorageStatusMaskBuilder builder) {
        return builder.build();
    }
}
