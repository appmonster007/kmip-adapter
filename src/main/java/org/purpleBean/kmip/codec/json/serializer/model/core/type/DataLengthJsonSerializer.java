package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DataLength;

public class DataLengthJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DataLength, Integer> {

    public DataLengthJsonSerializer() {
        super(DataLength::getValue);
    }
}