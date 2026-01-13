package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<KeyValuePresent, Boolean> {

    public KeyValuePresentTtlvSerializer() {
        super(KeyValuePresent::getValue);
    }
}