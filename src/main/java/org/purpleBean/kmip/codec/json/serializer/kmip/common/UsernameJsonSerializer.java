package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Username;

public class UsernameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Username, String> {

    public UsernameJsonSerializer() {
        super(Username::getValue);
    }
}