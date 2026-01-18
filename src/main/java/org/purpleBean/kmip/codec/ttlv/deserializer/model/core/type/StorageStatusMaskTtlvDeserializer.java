package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

public class StorageStatusMaskTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<StorageStatusMask, Integer> {

    public StorageStatusMaskTtlvDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType, Integer.class, value -> StorageStatusMask.builder().value(value).build());
    }
}