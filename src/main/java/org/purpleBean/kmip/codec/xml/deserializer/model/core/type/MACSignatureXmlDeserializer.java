package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MACSignature;

import java.nio.ByteBuffer;

public class MACSignatureXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MACSignature, ByteBuffer> {

    public MACSignatureXmlDeserializer() {
        super(MACSignature.kmipTag, MACSignature.encodingType, ByteBuffer.class, value -> MACSignature.builder().value(value).build());
    }
}