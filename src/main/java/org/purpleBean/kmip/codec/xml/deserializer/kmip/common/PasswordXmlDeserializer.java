package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.Password;

public class PasswordXmlDeserializer extends AbstractKmipXmlDeserializer<Password, String> {

    public PasswordXmlDeserializer() {
        super(Password.kmipTag, Password.encodingType, String.class, value -> Password.builder().value(value).build());
    }
}