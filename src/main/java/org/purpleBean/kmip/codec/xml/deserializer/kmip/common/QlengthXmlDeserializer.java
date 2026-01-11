package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthXmlDeserializer extends AbstractKmipXmlDeserializer<Qlength, Integer> {

    public QlengthXmlDeserializer() {
        super(Qlength.kmipTag, Qlength.encodingType, Integer.class, value -> Qlength.builder().value(value).build());
    }
}