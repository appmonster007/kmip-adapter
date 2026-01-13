package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.InitialCounterValue;

public class InitialCounterValueTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitialCounterValue, Integer> {

    public InitialCounterValueTtlvDeserializer() {
        super(InitialCounterValue.kmipTag, InitialCounterValue.encodingType, Integer.class, value -> InitialCounterValue.builder().value(value).build());
    }
}