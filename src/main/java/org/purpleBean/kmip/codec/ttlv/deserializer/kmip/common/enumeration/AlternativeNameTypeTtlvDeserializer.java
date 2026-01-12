package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

public class AlternativeNameTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<AlternativeNameType, Integer> {

    public AlternativeNameTypeTtlvDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType, Integer.class, value -> new AlternativeNameType(AlternativeNameType.fromValue(value)));
    }
}