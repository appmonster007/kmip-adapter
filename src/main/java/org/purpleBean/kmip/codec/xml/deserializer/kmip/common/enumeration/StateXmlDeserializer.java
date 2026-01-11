package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateXmlDeserializer extends AbstractKmipXmlDeserializer<State, String> {

    public StateXmlDeserializer() {
        super(State.kmipTag, State.encodingType, String.class, value -> new State(State.fromName(value)));
    }
}