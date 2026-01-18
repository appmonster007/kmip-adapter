package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

public class InitialCounterValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitialCounterValue, Integer> {

    public InitialCounterValueTtlvDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType, Integer.class, value -> InitialCounterValue.builder().value(value).build());
    }
}