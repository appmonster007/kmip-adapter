package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;

public class SecretDataTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SecretDataType, String> {

    public SecretDataTypeXmlDeserializer() {
        super(SecretDataType.kmipTag, SecretDataType.encodingType, String.class, value -> new SecretDataType(SecretDataType.fromName(value)));
    }
}