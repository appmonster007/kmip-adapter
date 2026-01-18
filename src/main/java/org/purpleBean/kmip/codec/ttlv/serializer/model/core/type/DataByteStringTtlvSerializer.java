package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.DataByteString;

import java.nio.ByteBuffer;

public class DataByteStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DataByteString, ByteBuffer> {

    public DataByteStringTtlvSerializer() {
        super(DataByteString::getValue);
    }
}