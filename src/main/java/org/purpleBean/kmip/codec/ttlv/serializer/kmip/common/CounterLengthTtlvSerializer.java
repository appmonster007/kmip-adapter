package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthTtlvSerializer extends AbstractKmipTtlvSerializer<CounterLength, Integer> {

    public CounterLengthTtlvSerializer() {
        super(CounterLength::getValue);
    }
}