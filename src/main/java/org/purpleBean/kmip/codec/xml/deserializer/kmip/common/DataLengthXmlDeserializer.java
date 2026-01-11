package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthXmlDeserializer extends AbstractKmipXmlDeserializer<DataLength, Integer> {

    public DataLengthXmlDeserializer() {
        super(DataLength.kmipTag, DataLength.encodingType, Integer.class, value -> DataLength.builder().value(value).build());
    }
}