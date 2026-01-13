package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NistKeyType, Integer> {

    public NistKeyTypeTtlvDeserializer() {
        super(NistKeyType.kmipTag, NistKeyType.encodingType, Integer.class, value -> new NistKeyType(NistKeyType.fromValue(value)));
    }
}