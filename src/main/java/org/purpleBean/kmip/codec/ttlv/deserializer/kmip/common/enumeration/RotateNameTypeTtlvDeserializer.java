package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RotateNameType, Integer> {

    public RotateNameTypeTtlvDeserializer() {
        super(RotateNameType.kmipTag, RotateNameType.encodingType, Integer.class, value -> new RotateNameType(RotateNameType.fromValue(value)));
    }
}