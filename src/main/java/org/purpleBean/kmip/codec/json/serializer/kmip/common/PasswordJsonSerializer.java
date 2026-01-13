package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Password;

public class PasswordJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Password, String> {

    public PasswordJsonSerializer() {
        super(Password::getValue);
    }
}