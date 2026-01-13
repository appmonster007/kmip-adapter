package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DataLength, Integer> {

    public DataLengthXmlDeserializer() {
        super(DataLength.kmipTag, DataLength.encodingType, Integer.class, value -> DataLength.builder().value(value).build());
    }
}