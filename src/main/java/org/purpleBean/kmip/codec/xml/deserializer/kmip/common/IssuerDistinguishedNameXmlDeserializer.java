package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameXmlDeserializer extends AbstractKmipXmlDeserializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameXmlDeserializer() {
        super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType, ByteBuffer.class, value -> IssuerDistinguishedName.builder().value(value).build());
    }
}