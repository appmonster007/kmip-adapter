package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthTtlvSerializer extends AbstractKmipTtlvSerializer<Qlength, Integer> {

    public QlengthTtlvSerializer() {
        super(Qlength::getValue);
    }
}