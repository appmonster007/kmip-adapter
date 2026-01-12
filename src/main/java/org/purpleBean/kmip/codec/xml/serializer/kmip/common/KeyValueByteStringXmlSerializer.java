package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringXmlSerializer extends AbstractKmipXmlSerializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringXmlSerializer() {
        super(KeyValueByteString::getValue);
    }
}