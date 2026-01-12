package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Password;

public class PasswordTtlvSerializer extends AbstractKmipTtlvSerializer<Password, String> {

    public PasswordTtlvSerializer() {
        super(Password::getValue);
    }
}