package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DataByteString, ByteBuffer> {

    public DataByteStringJsonSerializer() {
        super(DataByteString::getValue);
    }
}