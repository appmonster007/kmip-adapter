package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<InitialCounterValue, Integer> {

    public InitialCounterValueXmlDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType, Integer.class, value -> InitialCounterValue.builder().value(value).build());
    }
}