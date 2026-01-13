package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorJsonSerializer extends AbstractKmipDataTypeJsonSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorJsonSerializer() {
        super(InitializationVector::getValue);
    }
}