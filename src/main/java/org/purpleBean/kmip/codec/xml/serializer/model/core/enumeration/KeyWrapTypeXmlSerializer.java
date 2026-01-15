package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;

public class KeyWrapTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyWrapType, String> {

    public KeyWrapTypeXmlSerializer() {
        super(KeyWrapType::getDescription);
    }
}