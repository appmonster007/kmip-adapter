package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameTtlvDeserializer() {
        super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType, ByteBuffer.class, value -> IssuerDistinguishedName.builder().value(value).build());
    }
}