package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthTtlvDeserializer extends AbstractKmipTtlvDeserializer<DataLength, Integer> {

    public DataLengthTtlvDeserializer() {
        super(DataLength.kmipTag, DataLength.encodingType, Integer.class, value -> DataLength.builder().value(value).build());
    }
}