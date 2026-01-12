package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorTtlvSerializer extends AbstractKmipTtlvSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorTtlvSerializer() {
        super(InitializationVector::getValue);
    }
}