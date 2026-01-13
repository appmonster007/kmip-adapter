package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AlternativeNameType, Integer> {

    public AlternativeNameTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}