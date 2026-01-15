package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Password;

public class PasswordTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Password, String> {

    public PasswordTtlvSerializer() {
        super(Password::getValue);
    }
}