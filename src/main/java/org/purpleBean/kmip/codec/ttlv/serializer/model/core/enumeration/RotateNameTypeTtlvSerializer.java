package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;

public class RotateNameTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RotateNameType, Integer> {

    public RotateNameTypeTtlvSerializer() {
        super(RotateNameType::getIntValue);
    }
}