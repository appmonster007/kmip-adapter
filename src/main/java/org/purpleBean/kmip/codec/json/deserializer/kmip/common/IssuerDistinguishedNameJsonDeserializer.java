package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameJsonDeserializer extends AbstractKmipJsonDeserializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameJsonDeserializer() {
        super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType, ByteBuffer.class, value -> IssuerDistinguishedName.builder().value(value).build());
    }
}