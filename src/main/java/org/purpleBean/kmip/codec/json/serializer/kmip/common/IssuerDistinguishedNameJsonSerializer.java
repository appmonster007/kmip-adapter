package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameJsonSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}