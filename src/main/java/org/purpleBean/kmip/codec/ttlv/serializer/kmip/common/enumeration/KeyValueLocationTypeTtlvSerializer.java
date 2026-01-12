package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;

public class KeyValueLocationTypeTtlvSerializer extends AbstractKmipTtlvSerializer<KeyValueLocationType, Integer> {

    public KeyValueLocationTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}