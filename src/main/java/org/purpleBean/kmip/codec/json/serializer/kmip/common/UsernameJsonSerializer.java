package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Username;

public class UsernameJsonSerializer extends AbstractKmipJsonSerializer<Username, String> {

    public UsernameJsonSerializer() {
        super(Username::getValue);
    }
}