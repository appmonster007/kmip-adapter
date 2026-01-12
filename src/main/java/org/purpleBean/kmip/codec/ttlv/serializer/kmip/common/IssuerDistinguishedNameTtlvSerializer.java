package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameTtlvSerializer extends AbstractKmipTtlvSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameTtlvSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}