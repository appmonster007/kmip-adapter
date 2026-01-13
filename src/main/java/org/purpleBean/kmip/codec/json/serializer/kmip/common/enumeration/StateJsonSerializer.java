package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.State;

public class StateJsonSerializer extends AbstractKmipDataTypeJsonSerializer<State, String> {

    public StateJsonSerializer() {
        super(State::getDescription);
    }
}