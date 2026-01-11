package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeXmlDeserializer extends AbstractKmipXmlDeserializer<KeyRoleType, String> {

    public KeyRoleTypeXmlDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, String.class, value -> new KeyRoleType(KeyRoleType.fromName(value)));
    }
}