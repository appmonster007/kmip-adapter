package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.nio.ByteBuffer;

public class OpaqueDataValueXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<OpaqueDataValue, ByteBuffer> {

    public OpaqueDataValueXmlDeserializer() {
        super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType, ByteBuffer.class, value -> OpaqueDataValue.builder().value(value).build());
    }
}