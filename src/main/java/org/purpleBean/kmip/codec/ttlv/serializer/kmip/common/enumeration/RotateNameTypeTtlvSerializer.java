package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeTtlvSerializer extends AbstractKmipTtlvSerializer<RotateNameType, Integer> {

    public RotateNameTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}