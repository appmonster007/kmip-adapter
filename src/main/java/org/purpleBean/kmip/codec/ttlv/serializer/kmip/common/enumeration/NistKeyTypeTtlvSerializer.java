package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

public class NistKeyTypeTtlvSerializer extends AbstractKmipTtlvSerializer<NistKeyType, Integer> {

    public NistKeyTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}