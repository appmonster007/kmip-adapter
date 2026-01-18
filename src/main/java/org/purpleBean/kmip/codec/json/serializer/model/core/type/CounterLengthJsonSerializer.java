package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CounterLength;

public class CounterLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CounterLength, Integer> {

    public CounterLengthJsonSerializer() {
        super(CounterLength::getValue);
    }
}