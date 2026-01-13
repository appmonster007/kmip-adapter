package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<State, String> {

    public StateJsonDeserializer() {
        super(State.kmipTag, State.encodingType, String.class, value -> new State(State.fromName(value)));
    }
}