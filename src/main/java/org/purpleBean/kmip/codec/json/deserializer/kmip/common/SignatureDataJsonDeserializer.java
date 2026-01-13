package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SignatureData, ByteBuffer> {

    public SignatureDataJsonDeserializer() {
        super(SignatureData.kmipTag, SignatureData.encodingType, ByteBuffer.class, value -> SignatureData.builder().value(value).build());
    }
}