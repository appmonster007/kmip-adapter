package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DataByteString, ByteBuffer> {

    public DataByteStringJsonDeserializer() {
        super(DataByteString.kmipTag, DataByteString.encodingType, ByteBuffer.class, value -> DataByteString.builder().value(value).build());
    }
}