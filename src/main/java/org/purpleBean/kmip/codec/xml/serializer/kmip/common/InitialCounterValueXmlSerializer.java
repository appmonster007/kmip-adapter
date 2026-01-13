package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueXmlSerializer() {
        super(InitialCounterValue::getValue);
    }
}