package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Username;

public class UsernameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Username, String> {

    public UsernameJsonDeserializer() {
        super(Username.kmipTag, Username.encodingType, String.class, value -> Username.builder().value(value).build());
    }
}