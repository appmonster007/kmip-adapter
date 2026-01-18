package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.nio.ByteBuffer;

public class IssuerDistinguishedNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<IssuerDistinguishedName, ByteBuffer> {

    public IssuerDistinguishedNameJsonDeserializer() {
        super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType, ByteBuffer.class, value -> IssuerDistinguishedName.builder().value(value).build());
    }
}