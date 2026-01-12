package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringTtlvDeserializer extends AbstractKmipTtlvDeserializer<DataByteString, ByteBuffer> {

    public DataByteStringTtlvDeserializer() {
        super(DataByteString.kmipTag, DataByteString.encodingType, ByteBuffer.class, value -> DataByteString.builder().value(value).build());
    }
}