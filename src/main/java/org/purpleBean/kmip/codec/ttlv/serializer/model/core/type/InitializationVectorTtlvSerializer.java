package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.nio.ByteBuffer;

public class InitializationVectorTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<InitializationVector, ByteBuffer> {

    public InitializationVectorTtlvSerializer() {
        super(InitializationVector::getValue);
    }
}