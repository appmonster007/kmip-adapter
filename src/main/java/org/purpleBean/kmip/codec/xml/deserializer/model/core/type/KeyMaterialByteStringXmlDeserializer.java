package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;

import java.nio.ByteBuffer;

public class KeyMaterialByteStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyMaterialByteString, ByteBuffer> {

    public KeyMaterialByteStringXmlDeserializer() {
        super(KeyMaterialByteString.kmipTag, KeyMaterialByteString.encodingType, ByteBuffer.class, value -> KeyMaterialByteString.builder().value(value).build());
    }
}