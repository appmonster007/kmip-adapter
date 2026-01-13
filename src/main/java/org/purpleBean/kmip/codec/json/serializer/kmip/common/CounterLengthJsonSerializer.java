package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CounterLength, Integer> {

    public CounterLengthJsonSerializer() {
        super(CounterLength::getValue);
    }
}