package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

public class StorageStatusMaskJsonSerializer extends AbstractKmipDataTypeJsonSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskJsonSerializer() {
        super(StorageStatusMask::getValue);
    }
}