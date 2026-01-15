package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameTtlvSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}