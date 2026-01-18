package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MACSignature, ByteBuffer> {

    public MACSignatureJsonDeserializer() {
        super(MACSignature.kmipTag, MACSignature.encodingType, ByteBuffer.class, value -> MACSignature.builder().value(value).build());
    }
}