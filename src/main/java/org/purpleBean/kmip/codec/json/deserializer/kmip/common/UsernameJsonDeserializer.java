package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.Username;

public class UsernameJsonDeserializer extends AbstractKmipJsonDeserializer<Username, String> {

    public UsernameJsonDeserializer() {
        super(Username.kmipTag, Username.encodingType, String.class, value -> Username.builder().value(value).build());
    }
}