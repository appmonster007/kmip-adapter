package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueXmlDeserializer extends AbstractKmipXmlDeserializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueXmlDeserializer() {
        super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType, ByteBuffer.class, value -> OpaqueDataValue.builder().value(value).build());
    }
}