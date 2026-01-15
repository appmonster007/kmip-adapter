package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NameType, Integer> {

    public NameTypeTtlvDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, Integer.class, value -> new NameType(NameType.fromValue(value)));
    }
}