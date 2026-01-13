package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InitializationVector, ByteBuffer> {

    public InitializationVectorJsonDeserializer() {
        super(InitializationVector.kmipTag, InitializationVector.encodingType, ByteBuffer.class, value -> InitializationVector.builder().value(value).build());
    }
}