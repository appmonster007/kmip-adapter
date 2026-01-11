package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

public class SecretDataTypeXmlDeserializer extends AbstractKmipXmlDeserializer<SecretDataType, String> {

    public SecretDataTypeXmlDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType, String.class, value -> new SecretDataType(SecretDataType.fromName(value)));
    }
}