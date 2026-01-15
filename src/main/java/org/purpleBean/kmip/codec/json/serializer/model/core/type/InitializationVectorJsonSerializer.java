package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorJsonSerializer() {
        super(InitializationVector::getValue);
    }
}