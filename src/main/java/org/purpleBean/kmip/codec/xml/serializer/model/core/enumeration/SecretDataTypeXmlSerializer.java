package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

public class SecretDataTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SecretDataType, String> {

    public SecretDataTypeXmlSerializer() {
        super(SecretDataType::getDescription);
    }
}