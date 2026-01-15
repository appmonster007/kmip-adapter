package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.DataLength;

public class DataLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DataLength, Integer> {

    public DataLengthTtlvSerializer() {
        super(DataLength::getValue);
    }
}