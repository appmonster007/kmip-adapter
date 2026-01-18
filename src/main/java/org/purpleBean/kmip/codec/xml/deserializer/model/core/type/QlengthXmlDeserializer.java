package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Qlength;

public class QlengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Qlength, Integer> {

    public QlengthXmlDeserializer() {
        super(Qlength.kmipTag, Qlength.encodingType, Integer.class, value -> Qlength.builder().value(value).build());
    }
}