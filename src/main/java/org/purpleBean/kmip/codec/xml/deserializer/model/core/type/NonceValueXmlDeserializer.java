package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.nio.ByteBuffer;

public class NonceValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NonceValue, ByteBuffer> {

    public NonceValueXmlDeserializer() {
        super(NonceValue.kmipTag, NonceValue.encodingType, ByteBuffer.class, value -> NonceValue.builder().value(value).build());
    }
}