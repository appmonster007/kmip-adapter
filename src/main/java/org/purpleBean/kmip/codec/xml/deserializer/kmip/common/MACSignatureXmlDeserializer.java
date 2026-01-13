package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MACSignature, ByteBuffer> {

    public MACSignatureXmlDeserializer() {
        super(MACSignature.kmipTag, MACSignature.encodingType, ByteBuffer.class, value -> MACSignature.builder().value(value).build());
    }
}