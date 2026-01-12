package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskJsonSerializer extends AbstractKmipJsonSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskJsonSerializer() {
        super(StorageStatusMask::getValue);
    }
}