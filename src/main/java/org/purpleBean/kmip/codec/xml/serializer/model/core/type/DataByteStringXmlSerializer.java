package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<DataByteString, ByteBuffer> {

    public DataByteStringXmlSerializer() {
        super(DataByteString::getValue);
    }
}