package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NameType, Integer> {

    public NameTypeTtlvDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, Integer.class, value -> NameType.fromValue(value).inst());
    }
}