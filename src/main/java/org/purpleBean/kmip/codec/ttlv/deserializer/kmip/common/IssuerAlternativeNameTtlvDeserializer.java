package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameTtlvDeserializer() {
        super(IssuerAlternativeName.kmipTag, IssuerAlternativeName.encodingType, ByteBuffer.class, value -> IssuerAlternativeName.builder().value(value).build());
    }
}