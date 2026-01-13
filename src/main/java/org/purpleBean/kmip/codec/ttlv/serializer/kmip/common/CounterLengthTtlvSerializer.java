package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CounterLength, Integer> {

    public CounterLengthTtlvSerializer() {
        super(CounterLength::getValue);
    }
}