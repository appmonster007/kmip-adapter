package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorTtlvDeserializer extends AbstractKmipTtlvDeserializer<InitializationVector, ByteBuffer> {

    public InitializationVectorTtlvDeserializer() {
        super(InitializationVector.kmipTag, InitializationVector.encodingType, ByteBuffer.class, value -> InitializationVector.builder().value(value).build());
    }
}