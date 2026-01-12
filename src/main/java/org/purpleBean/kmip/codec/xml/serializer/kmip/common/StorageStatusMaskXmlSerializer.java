package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.StorageStatusMask;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class StorageStatusMaskXmlSerializer extends AbstractKmipXmlSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskXmlSerializer() {
        super(StorageStatusMask::getValue);
    }
}