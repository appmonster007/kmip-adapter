package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthTtlvDeserializer extends AbstractKmipTtlvDeserializer<CounterLength, Integer> {

    public CounterLengthTtlvDeserializer() {
        super(CounterLength.kmipTag, CounterLength.encodingType, Integer.class, value -> CounterLength.builder().value(value).build());
    }
}