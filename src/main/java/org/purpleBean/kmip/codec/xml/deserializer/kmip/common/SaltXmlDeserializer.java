package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.Salt;

import java.nio.ByteBuffer;

public class SaltXmlDeserializer extends AbstractKmipXmlDeserializer<Salt, ByteBuffer> {

    public SaltXmlDeserializer() {
        super(Salt.kmipTag, Salt.encodingType, ByteBuffer.class, value -> Salt.builder().value(value).build());
    }
}