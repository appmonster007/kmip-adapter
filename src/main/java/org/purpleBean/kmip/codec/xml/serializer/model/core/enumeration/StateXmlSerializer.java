package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.State;

public class StateXmlSerializer extends AbstractKmipDataTypeXmlSerializer<State, String> {

    public StateXmlSerializer() {
        super(State::getDescription);
    }
}