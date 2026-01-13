package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MACSignature, ByteBuffer> {

    public MACSignatureJsonSerializer() {
        super(MACSignature::getValue);
    }
}