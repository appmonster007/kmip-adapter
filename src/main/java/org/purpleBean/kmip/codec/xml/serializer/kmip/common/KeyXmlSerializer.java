package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Key, ByteBuffer> {

    public KeyXmlSerializer() {
        super(Key::getValue);
    }
}