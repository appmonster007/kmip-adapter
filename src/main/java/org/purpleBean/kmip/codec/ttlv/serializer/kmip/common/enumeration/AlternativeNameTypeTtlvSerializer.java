package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeTtlvSerializer extends AbstractKmipTtlvSerializer<AlternativeNameType, Integer> {

    public AlternativeNameTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}