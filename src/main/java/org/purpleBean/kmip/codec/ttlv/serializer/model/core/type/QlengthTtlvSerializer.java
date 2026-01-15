package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Qlength;

public class QlengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Qlength, Integer> {

    public QlengthTtlvSerializer() {
        super(Qlength::getValue);
    }
}