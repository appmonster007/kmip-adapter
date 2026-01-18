package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CounterLength;

public class CounterLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CounterLength, Integer> {

    public CounterLengthTtlvSerializer() {
        super(CounterLength::getValue);
    }
}