package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MACSignature, ByteBuffer> {

    public MACSignatureTtlvSerializer() {
        super(MACSignature::getValue);
    }
}