package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.Password;

public class PasswordJsonDeserializer extends AbstractKmipJsonDeserializer<Password, String> {

    public PasswordJsonDeserializer() {
        super(Password.kmipTag, Password.encodingType, String.class, value -> Password.builder().value(value).build());
    }
}