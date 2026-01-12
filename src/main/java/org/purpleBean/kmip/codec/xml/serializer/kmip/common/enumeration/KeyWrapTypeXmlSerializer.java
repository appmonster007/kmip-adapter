package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyWrapType;

public class KeyWrapTypeXmlSerializer extends AbstractKmipXmlSerializer<KeyWrapType, String> {

    public KeyWrapTypeXmlSerializer() {
        super(KeyWrapType::getDescription);
    }
}