package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameJsonDeserializer() {
        super(IssuerAlternativeName.kmipTag, IssuerAlternativeName.encodingType, ByteBuffer.class, value -> IssuerAlternativeName.builder().value(value).build());
    }
}