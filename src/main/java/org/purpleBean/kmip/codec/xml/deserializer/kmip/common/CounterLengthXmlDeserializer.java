package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthXmlDeserializer extends AbstractKmipXmlDeserializer<CounterLength, Integer> {

    public CounterLengthXmlDeserializer() {
        super(CounterLength.kmipTag, CounterLength.encodingType, Integer.class, value -> CounterLength.builder().value(value).build());
    }
}