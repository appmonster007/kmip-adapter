package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameJsonSerializer extends AbstractKmipJsonSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameJsonSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}