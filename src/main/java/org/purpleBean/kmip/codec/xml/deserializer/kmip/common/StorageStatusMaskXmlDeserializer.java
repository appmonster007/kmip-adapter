package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskXmlDeserializer extends AbstractKmipXmlDeserializer<StorageStatusMask, Integer> {

    public StorageStatusMaskXmlDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType, Integer.class, value -> StorageStatusMask.builder().value(value).build());
    }
}