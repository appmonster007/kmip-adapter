package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorTtlvSerializer() {
        super(InitializationVector::getValue);
    }
}