package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueXmlSerializer extends AbstractKmipXmlSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueXmlSerializer() {
        super(InitialCounterValue::getValue);
    }
}