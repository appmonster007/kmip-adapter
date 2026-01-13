package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Key;

import java.nio.ByteBuffer;

public class KeyXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Key, ByteBuffer> {

    public KeyXmlDeserializer() {
        super(Key.kmipTag, Key.encodingType, ByteBuffer.class, value -> Key.builder().value(value).build());
    }
}