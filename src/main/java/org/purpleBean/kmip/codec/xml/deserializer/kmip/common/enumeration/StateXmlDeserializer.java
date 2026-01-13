package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<State, String> {

    public StateXmlDeserializer() {
        super(State.kmipTag, State.encodingType, String.class, value -> new State(State.fromName(value)));
    }
}