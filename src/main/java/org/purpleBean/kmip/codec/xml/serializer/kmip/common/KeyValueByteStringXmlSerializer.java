package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.KeyValueByteString;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import java.nio.ByteBuffer;

public class KeyValueByteStringXmlSerializer extends AbstractKmipXmlSerializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringXmlSerializer() {
        super(KeyValueByteString::getValue);
    }
}