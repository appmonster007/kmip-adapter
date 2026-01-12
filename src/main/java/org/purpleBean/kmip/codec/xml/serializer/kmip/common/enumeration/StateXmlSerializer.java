package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class StateXmlSerializer extends AbstractKmipXmlSerializer<State, String> {

    public StateXmlSerializer() {
        super(State::getDescription);
    }
}