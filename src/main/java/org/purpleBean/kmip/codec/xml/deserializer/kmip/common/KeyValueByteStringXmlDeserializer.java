package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringXmlDeserializer extends AbstractKmipXmlDeserializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringXmlDeserializer() {
        super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType, ByteBuffer.class, value -> KeyValueByteString.builder().value(value).build());
    }
}