package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.SecretDataType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class SecretDataTypeXmlSerializer extends AbstractKmipXmlSerializer<SecretDataType, String> {

    public SecretDataTypeXmlSerializer() {
        super(SecretDataType::getDescription);
    }
}