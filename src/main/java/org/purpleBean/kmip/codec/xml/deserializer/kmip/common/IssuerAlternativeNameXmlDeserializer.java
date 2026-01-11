package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.nio.ByteBuffer;

public class IssuerAlternativeNameXmlDeserializer extends AbstractKmipXmlDeserializer<IssuerAlternativeName, ByteBuffer> {

    public IssuerAlternativeNameXmlDeserializer() {
        super(IssuerAlternativeName.kmipTag, IssuerAlternativeName.encodingType, ByteBuffer.class, value -> IssuerAlternativeName.builder().value(value).build());
    }
}