package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskJsonDeserializer extends AbstractKmipJsonDeserializer<StorageStatusMask, Integer> {

    public StorageStatusMaskJsonDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType, Integer.class, value -> StorageStatusMask.builder().value(value).build());
    }
}