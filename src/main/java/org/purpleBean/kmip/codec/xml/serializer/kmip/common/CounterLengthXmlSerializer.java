package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CounterLength;

public class CounterLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CounterLength, Integer> {

    public CounterLengthXmlSerializer() {
        super(CounterLength::getValue);
    }
}