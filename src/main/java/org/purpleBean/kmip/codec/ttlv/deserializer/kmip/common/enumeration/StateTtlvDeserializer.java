package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateTtlvDeserializer extends AbstractKmipTtlvDeserializer<State, Integer> {

    public StateTtlvDeserializer() {
        super(State.kmipTag, State.encodingType, Integer.class, value -> new State(State.fromValue(value)));
    }
}