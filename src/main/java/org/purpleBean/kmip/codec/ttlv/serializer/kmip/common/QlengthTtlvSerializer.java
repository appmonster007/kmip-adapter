package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Qlength, Integer> {

    public QlengthTtlvSerializer() {
        super(Qlength::getValue);
    }
}