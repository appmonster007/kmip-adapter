package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DataLength;

public class DataLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DataLength, Integer> {

    public DataLengthJsonSerializer() {
        super(DataLength::getValue);
    }
}