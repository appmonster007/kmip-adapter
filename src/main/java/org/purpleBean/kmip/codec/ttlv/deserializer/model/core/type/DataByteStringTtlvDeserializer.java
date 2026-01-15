package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DataByteString, ByteBuffer> {

    public DataByteStringTtlvDeserializer() {
        super(DataByteString.kmipTag, DataByteString.encodingType, ByteBuffer.class, value -> DataByteString.builder().value(value).build());
    }
}