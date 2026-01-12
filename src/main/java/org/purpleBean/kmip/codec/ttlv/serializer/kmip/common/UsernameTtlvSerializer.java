package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Username;

public class UsernameTtlvSerializer extends AbstractKmipTtlvSerializer<Username, String> {

    public UsernameTtlvSerializer() {
        super(Username::getValue);
    }
}