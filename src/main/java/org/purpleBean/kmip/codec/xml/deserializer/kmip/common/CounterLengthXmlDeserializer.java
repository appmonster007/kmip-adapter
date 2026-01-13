package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CounterLength, Integer> {

    public CounterLengthXmlDeserializer() {
        super(CounterLength.kmipTag, CounterLength.encodingType, Integer.class, value -> CounterLength.builder().value(value).build());
    }
}