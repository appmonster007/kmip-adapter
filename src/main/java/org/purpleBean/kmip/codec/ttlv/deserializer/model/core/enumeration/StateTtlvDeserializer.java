package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.State;

public class StateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<State, Integer> {

    public StateTtlvDeserializer() {
        super(State.kmipTag, State.encodingType, Integer.class, value -> State.fromValue(value).inst());
    }
}