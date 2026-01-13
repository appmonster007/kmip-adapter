package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameTtlvSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}