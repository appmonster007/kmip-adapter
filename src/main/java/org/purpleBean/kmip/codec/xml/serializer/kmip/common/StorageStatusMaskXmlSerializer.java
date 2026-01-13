package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskXmlSerializer extends AbstractKmipDataTypeXmlSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskXmlSerializer() {
        super(StorageStatusMask::getValue);
    }
}