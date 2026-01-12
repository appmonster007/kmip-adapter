package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskTtlvSerializer extends AbstractKmipTtlvSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskTtlvSerializer() {
        super(StorageStatusMask::getValue);
    }
}