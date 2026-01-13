package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Qlength, Integer> {

    public QlengthXmlDeserializer() {
        super(Qlength.kmipTag, Qlength.encodingType, Integer.class, value -> Qlength.builder().value(value).build());
    }
}