package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeXmlSerializer extends AbstractKmipXmlSerializer<KeyRoleType, String> {

    public KeyRoleTypeXmlSerializer() {
        super(KeyRoleType::getDescription);
    }
}