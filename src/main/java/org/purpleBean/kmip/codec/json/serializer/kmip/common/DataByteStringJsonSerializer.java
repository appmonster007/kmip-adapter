package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringJsonSerializer extends AbstractKmipJsonSerializer<DataByteString, ByteBuffer> {

    public DataByteStringJsonSerializer() {
        super(DataByteString::getValue);
    }
}