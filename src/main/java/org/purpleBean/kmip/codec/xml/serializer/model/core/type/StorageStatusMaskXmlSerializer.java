package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

public class StorageStatusMaskXmlSerializer extends AbstractKmipDataTypeXmlSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskXmlSerializer() {
        super(StorageStatusMask::getValue);
    }
}