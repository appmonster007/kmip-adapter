package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InitialCounterValue, Integer> {

    public InitialCounterValueJsonDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType, Integer.class, value -> InitialCounterValue.builder().value(value).build());
    }
}