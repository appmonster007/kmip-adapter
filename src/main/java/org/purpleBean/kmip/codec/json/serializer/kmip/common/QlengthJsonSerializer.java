package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthJsonSerializer extends AbstractKmipJsonSerializer<Qlength, Integer> {

    public QlengthJsonSerializer() {
        super(Qlength::getValue);
    }
}