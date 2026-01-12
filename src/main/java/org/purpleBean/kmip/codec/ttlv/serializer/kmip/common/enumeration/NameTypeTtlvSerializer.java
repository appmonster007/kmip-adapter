package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeTtlvSerializer extends AbstractKmipTtlvSerializer<NameType, Integer> {

    public NameTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}