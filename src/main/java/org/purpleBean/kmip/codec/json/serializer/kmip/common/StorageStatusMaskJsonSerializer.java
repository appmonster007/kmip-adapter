package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskJsonSerializer extends AbstractKmipDataTypeJsonSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskJsonSerializer() {
        super(StorageStatusMask::getValue);
    }
}