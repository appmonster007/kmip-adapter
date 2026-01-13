package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SecretDataType, String> {

    public SecretDataTypeXmlSerializer() {
        super(SecretDataType::getDescription);
    }
}