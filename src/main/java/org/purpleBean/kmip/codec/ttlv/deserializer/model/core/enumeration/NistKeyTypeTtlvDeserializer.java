package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;

public class NistKeyTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NistKeyType, Integer> {

    public NistKeyTypeTtlvDeserializer() {
        super(NistKeyType.kmipTag, NistKeyType.encodingType, Integer.class, value -> NistKeyType.fromValue(value).inst());
    }
}