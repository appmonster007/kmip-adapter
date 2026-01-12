package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Username;

public class UsernameTtlvDeserializer extends AbstractKmipTtlvDeserializer<Username, String> {

    public UsernameTtlvDeserializer() {
        super(Username.kmipTag, Username.encodingType, String.class, value -> Username.builder().value(value).build());
    }
}