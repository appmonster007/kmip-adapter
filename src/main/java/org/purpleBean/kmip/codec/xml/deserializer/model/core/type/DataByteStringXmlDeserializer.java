package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DataByteString, ByteBuffer> {

    public DataByteStringXmlDeserializer() {
        super(DataByteString.kmipTag, DataByteString.encodingType, ByteBuffer.class, value -> DataByteString.builder().value(value).build());
    }
}