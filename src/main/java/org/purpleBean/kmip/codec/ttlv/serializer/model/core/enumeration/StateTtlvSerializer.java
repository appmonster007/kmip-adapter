package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.State;

public class StateTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<State, Integer> {

    public StateTtlvSerializer() {
        super(State::getValue);
    }
}