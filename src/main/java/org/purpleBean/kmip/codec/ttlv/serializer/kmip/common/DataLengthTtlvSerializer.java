package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthTtlvSerializer extends AbstractKmipTtlvSerializer<DataLength, Integer> {

    public DataLengthTtlvSerializer() {
        super(DataLength::getValue);
    }
}