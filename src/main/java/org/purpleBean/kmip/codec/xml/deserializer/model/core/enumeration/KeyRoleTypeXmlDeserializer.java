package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyRoleType, String> {

    public KeyRoleTypeXmlDeserializer() {
        super(KeyRoleType.kmipTag, KeyRoleType.encodingType, String.class, value -> new KeyRoleType(KeyRoleType.fromName(value)));
    }
}