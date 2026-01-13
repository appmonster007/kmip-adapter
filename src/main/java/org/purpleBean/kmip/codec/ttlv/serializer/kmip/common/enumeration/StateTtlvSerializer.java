package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<State, Integer> {

    public StateTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}