package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Qlength;

public class QlengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Qlength, Integer> {

    public QlengthJsonSerializer() {
        super(Qlength::getValue);
    }
}