package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Password;

public class PasswordTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Password, String> {

    public PasswordTtlvSerializer() {
        super(Password::getValue);
    }
}