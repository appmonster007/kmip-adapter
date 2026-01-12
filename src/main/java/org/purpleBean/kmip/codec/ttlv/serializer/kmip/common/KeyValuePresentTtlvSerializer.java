package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentTtlvSerializer extends AbstractKmipTtlvSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentTtlvSerializer() {
        super(KeyValuePresent::getValue);
    }
}