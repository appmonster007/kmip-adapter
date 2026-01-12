package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<NameType, Integer> {

    public NameTypeTtlvDeserializer() {
        super(NameType.kmipTag, NameType.encodingType, Integer.class, value -> new NameType(NameType.fromValue(value)));
    }
}