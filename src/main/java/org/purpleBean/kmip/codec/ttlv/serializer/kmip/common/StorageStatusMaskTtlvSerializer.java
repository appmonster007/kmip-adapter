package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskTtlvSerializer() {
        super(StorageStatusMask::getValue);
    }
}