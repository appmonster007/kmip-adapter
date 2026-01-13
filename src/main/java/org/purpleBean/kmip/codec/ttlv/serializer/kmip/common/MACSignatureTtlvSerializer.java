package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<MACSignature, ByteBuffer> {

    public MACSignatureTtlvSerializer() {
        super(MACSignature::getValue);
    }
}