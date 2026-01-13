package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DataByteString, ByteBuffer> {

    public DataByteStringXmlSerializer() {
        super(DataByteString::getValue);
    }
}