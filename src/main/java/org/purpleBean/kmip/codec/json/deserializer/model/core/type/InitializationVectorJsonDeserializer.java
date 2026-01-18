package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<InitializationVector, ByteBuffer> {

    public InitializationVectorJsonDeserializer() {
        super(InitializationVector.kmipTag, InitializationVector.encodingType, ByteBuffer.class, value -> InitializationVector.builder().value(value).build());
    }
}