package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Password;

public class PasswordJsonSerializer extends AbstractKmipJsonSerializer<Password, String> {

    public PasswordJsonSerializer() {
        super(Password::getValue);
    }
}