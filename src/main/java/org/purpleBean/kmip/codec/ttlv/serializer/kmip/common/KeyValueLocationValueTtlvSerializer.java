package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueTtlvSerializer extends AbstractKmipTtlvSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueTtlvSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}