package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyRoleType, String> {

    public KeyRoleTypeXmlDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, String.class, value -> new KeyRoleType(KeyRoleType.fromName(value)));
    }
}