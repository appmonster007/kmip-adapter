package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DataLength, Integer> {

    public DataLengthJsonDeserializer() {
        super(DataLength.kmipTag, DataLength.encodingType, Integer.class, value -> DataLength.builder().value(value).build());
    }
}