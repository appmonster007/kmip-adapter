package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.Password;

public class PasswordTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Password, String> {

    public PasswordTtlvDeserializer() {
        super(Password.kmipTag, Password.encodingType, String.class, value -> Password.builder().value(value).build());
    }
}