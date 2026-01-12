package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureJsonSerializer extends AbstractKmipJsonSerializer<MACSignature, ByteBuffer> {

    public MACSignatureJsonSerializer() {
        super(MACSignature::getValue);
    }
}