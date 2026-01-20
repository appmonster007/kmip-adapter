package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AlternativeNameType, Integer> {

    public AlternativeNameTypeTtlvDeserializer() {
        super(AlternativeNameType.kmipTag, AlternativeNameType.encodingType, Integer.class, value -> AlternativeNameType.fromValue(value).inst());
    }
}