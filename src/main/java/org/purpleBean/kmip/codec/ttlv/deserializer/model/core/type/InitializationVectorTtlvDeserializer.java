package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<InitializationVector, ByteBuffer> {

    public InitializationVectorTtlvDeserializer() {
        super(InitializationVector.kmipTag, InitializationVector.encodingType, ByteBuffer.class, value -> InitializationVector.builder().value(value).build());
    }
}