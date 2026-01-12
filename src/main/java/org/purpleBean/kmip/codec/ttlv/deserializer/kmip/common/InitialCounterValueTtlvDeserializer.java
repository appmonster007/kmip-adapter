package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueTtlvDeserializer extends AbstractKmipTtlvDeserializer<InitialCounterValue, Integer> {

    public InitialCounterValueTtlvDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType, Integer.class, value -> InitialCounterValue.builder().value(value).build());
    }
}