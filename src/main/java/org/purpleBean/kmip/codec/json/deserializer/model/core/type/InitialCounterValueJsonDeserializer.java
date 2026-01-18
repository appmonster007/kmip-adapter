package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

public class InitialCounterValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InitialCounterValue, Integer> {

    public InitialCounterValueJsonDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType, Integer.class, value -> InitialCounterValue.builder().value(value).build());
    }
}