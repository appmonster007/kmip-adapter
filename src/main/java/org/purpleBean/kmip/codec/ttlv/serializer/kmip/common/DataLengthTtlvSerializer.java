package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DataLength, Integer> {

    public DataLengthTtlvSerializer() {
        super(DataLength::getValue);
    }
}