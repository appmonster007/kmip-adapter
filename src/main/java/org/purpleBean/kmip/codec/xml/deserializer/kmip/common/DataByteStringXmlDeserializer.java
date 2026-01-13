package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DataByteString, ByteBuffer> {

    public DataByteStringXmlDeserializer() {
        super(DataByteString.kmipTag, DataByteString.encodingType, ByteBuffer.class, value -> DataByteString.builder().value(value).build());
    }
}