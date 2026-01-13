package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<StorageStatusMask, Integer> {

    public StorageStatusMaskJsonDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType, Integer.class, value -> StorageStatusMask.builder().value(value).build());
    }
}