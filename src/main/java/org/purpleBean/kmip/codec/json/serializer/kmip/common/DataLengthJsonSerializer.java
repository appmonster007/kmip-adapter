package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthJsonSerializer extends AbstractKmipJsonSerializer<DataLength, Integer> {

    public DataLengthJsonSerializer() {
        super(DataLength::getValue);
    }
}