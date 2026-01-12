package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.DataByteString;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class DataByteStringXmlSerializer extends AbstractKmipXmlSerializer<DataByteString, ByteBuffer> {

    public DataByteStringXmlSerializer() {
        super(DataByteString::getValue);
    }
}