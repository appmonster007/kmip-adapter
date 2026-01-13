package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CounterLength, Integer> {

    public CounterLengthJsonDeserializer() {
        super(CounterLength.kmipTag, CounterLength.encodingType, Integer.class, value -> CounterLength.builder().value(value).build());
    }
}