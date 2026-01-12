package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthJsonSerializer extends AbstractKmipJsonSerializer<CounterLength, Integer> {

    public CounterLengthJsonSerializer() {
        super(CounterLength::getValue);
    }
}