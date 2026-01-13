package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<StorageStatusMask, Integer> {

    public StorageStatusMaskTtlvDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType, Integer.class, value -> StorageStatusMask.builder().value(value).build());
    }
}