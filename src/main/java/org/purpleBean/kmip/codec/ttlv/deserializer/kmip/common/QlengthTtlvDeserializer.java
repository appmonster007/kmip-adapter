package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Qlength, Integer> {

    public QlengthTtlvDeserializer() {
        super(Qlength.kmipTag, Qlength.encodingType, Integer.class, value -> Qlength.builder().value(value).build());
    }
}