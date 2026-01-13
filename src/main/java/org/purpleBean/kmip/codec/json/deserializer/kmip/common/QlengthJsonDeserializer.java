package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Qlength, Integer> {

    public QlengthJsonDeserializer() {
        super(Qlength.kmipTag, Qlength.encodingType, Integer.class, value -> Qlength.builder().value(value).build());
    }
}