package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<NameType, Integer> {

    public NameTypeTtlvSerializer() {
        super(NameType::getValue);
    }
}