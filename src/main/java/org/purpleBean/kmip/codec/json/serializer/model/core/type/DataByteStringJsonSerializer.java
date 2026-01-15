package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DataByteString, ByteBuffer> {

    public DataByteStringJsonSerializer() {
        super(DataByteString::getValue);
    }
}