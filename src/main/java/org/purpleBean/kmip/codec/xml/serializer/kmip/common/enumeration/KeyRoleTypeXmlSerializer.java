package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.KeyRoleType;

public class KeyRoleTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyRoleType, String> {

    public KeyRoleTypeXmlSerializer() {
        super(KeyRoleType::getDescription);
    }
}