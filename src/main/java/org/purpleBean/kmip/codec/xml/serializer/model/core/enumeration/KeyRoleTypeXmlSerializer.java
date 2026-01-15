package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;

public class KeyRoleTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<KeyRoleType, String> {

    public KeyRoleTypeXmlSerializer() {
        super(KeyRoleType::getDescription);
    }
}