package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateTtlvSerializer extends AbstractKmipTtlvSerializer<State, Integer> {

    public StateTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}