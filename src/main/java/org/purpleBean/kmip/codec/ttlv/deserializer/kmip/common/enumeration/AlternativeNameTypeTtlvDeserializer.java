package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AlternativeNameType, Integer> {

    public AlternativeNameTypeTtlvDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType, Integer.class, value -> new AlternativeNameType(AlternativeNameType.fromValue(value)));
    }
}