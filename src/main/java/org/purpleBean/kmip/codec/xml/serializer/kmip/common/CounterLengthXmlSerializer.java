package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CounterLength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class CounterLengthXmlSerializer extends AbstractKmipXmlSerializer<CounterLength, Integer> {

    public CounterLengthXmlSerializer() {
        super(CounterLength::getValue);
    }
}