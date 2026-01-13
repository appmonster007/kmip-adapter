package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<State, Integer> {

    public StateTtlvDeserializer() {
        super(State.kmipTag, State.encodingType, Integer.class, value -> new State(State.fromValue(value)));
    }
}