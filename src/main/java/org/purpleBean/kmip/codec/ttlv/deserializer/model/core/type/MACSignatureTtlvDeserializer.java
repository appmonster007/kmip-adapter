package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MACSignature, ByteBuffer> {

    public MACSignatureTtlvDeserializer() {
        super(MACSignature.kmipTag, MACSignature.encodingType, ByteBuffer.class, value -> MACSignature.builder().value(value).build());
    }
}