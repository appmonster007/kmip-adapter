package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringXmlDeserializer extends AbstractKmipXmlDeserializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringXmlDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType, ByteBuffer.class, value -> KeyMaterialByteString.builder().value(value).build());
    }
}