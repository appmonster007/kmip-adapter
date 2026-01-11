package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthJsonDeserializer extends AbstractKmipJsonDeserializer<DataLength, Integer> {

    public DataLengthJsonDeserializer() {
        super(DataLength.kmipTag, DataLength.encodingType, Integer.class, value -> DataLength.builder().value(value).build());
    }
}