package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CounterLength;

public class CounterLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CounterLength, Integer> {

    public CounterLengthXmlSerializer() {
        super(CounterLength::getValue);
    }
}