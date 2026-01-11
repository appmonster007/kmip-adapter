package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataJsonDeserializer extends AbstractKmipJsonDeserializer<SignatureData, ByteBuffer> {

    public SignatureDataJsonDeserializer() {
        super(SignatureData.kmipTag, SignatureData.encodingType, ByteBuffer.class, value -> SignatureData.builder().value(value).build());
    }
}