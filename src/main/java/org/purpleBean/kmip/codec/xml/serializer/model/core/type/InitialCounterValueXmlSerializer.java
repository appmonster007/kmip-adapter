package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

public class InitialCounterValueXmlSerializer extends AbstractKmipDataTypeXmlSerializer<InitialCounterValue, Integer> {

    public InitialCounterValueXmlSerializer() {
        super(InitialCounterValue::getValue);
    }
}