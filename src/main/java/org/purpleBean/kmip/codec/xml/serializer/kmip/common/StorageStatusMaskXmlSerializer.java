package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskXmlSerializer extends AbstractKmipXmlSerializer<StorageStatusMask, Integer> {

    public StorageStatusMaskXmlSerializer() {
        super(StorageStatusMask::getValue);
    }
}