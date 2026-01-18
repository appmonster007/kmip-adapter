package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;

import java.nio.ByteBuffer;

public class KeyValueByteStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<KeyValueByteString, ByteBuffer> {

    public KeyValueByteStringXmlDeserializer() {
        super(KeyValueByteString.kmipTag, KeyValueByteString.encodingType, ByteBuffer.class, value -> KeyValueByteString.builder().value(value).build());
    }
}