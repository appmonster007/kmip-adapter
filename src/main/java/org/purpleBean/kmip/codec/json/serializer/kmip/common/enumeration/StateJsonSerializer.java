package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateJsonSerializer extends AbstractKmipJsonSerializer<State, String> {

    public StateJsonSerializer() {
        super(State::getDescription);
    }
}