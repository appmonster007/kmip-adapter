package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

public class StorageStatusMaskXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<StorageStatusMask, String> {

    public StorageStatusMaskXmlDeserializer() {
        super(StorageStatusMask.kmipTag, StorageStatusMask.encodingType, String.class, StorageStatusMask::fromMaskString);
    }
}