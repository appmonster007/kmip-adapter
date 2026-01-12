package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<NistKeyType, Integer> {

    public NistKeyTypeTtlvDeserializer() {
        super(NistKeyType.kmipTag, NistKeyType.encodingType, Integer.class, value -> new NistKeyType(NistKeyType.fromValue(value)));
    }
}