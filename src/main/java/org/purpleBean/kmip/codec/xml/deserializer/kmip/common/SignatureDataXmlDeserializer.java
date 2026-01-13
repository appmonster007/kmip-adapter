package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SignatureData, ByteBuffer> {

    public SignatureDataXmlDeserializer() {
        super(SignatureData.kmipTag, SignatureData.encodingType, ByteBuffer.class, value -> SignatureData.builder().value(value).build());
    }
}