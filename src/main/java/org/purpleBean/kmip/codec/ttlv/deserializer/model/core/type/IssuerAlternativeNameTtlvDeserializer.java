package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameTtlvDeserializer() {
        super(IssuerAlternativeName.kmipTag, IssuerAlternativeName.encodingType, ByteBuffer.class, value -> IssuerAlternativeName.builder().value(value).build());
    }
}