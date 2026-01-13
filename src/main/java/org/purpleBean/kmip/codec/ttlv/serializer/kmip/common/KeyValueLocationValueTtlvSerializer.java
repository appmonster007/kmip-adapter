package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyValueLocationValue, String> {

    public KeyValueLocationValueTtlvSerializer() {
        super(KeyValueLocationValue::getValue);
    }
}