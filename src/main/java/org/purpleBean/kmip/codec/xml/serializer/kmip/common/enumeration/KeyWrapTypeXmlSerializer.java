package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyWrapType, String> {

    public KeyWrapTypeXmlSerializer() {
        super(KeyWrapType::getDescription);
    }
}