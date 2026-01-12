package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameTtlvDeserializer() {
        super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType, ByteBuffer.class, value -> IssuerDistinguishedName.builder().value(value).build());
    }
}