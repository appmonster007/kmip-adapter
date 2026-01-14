package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<NameType, Integer> {

    public NameTypeTtlvSerializer() {
        super(NameType::getValue);
    }
}