package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameJsonSerializer extends AbstractKmipDataTypeJsonSerializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameJsonSerializer() {
        super(IssuerDistinguishedName::getValue);
    }
}