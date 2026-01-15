package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SignatureData;

import java.nio.ByteBuffer;

public class SignatureDataXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SignatureData, ByteBuffer> {

    public SignatureDataXmlDeserializer() {
        super(SignatureData.kmipTag, SignatureData.encodingType, ByteBuffer.class, value -> SignatureData.builder().value(value).build());
    }
}