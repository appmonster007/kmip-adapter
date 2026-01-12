package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Password;

public class PasswordTtlvDeserializer extends AbstractKmipTtlvDeserializer<Password, String> {

    public PasswordTtlvDeserializer() {
        super(Password.kmipTag, Password.encodingType, String.class, value -> Password.builder().value(value).build());
    }
}