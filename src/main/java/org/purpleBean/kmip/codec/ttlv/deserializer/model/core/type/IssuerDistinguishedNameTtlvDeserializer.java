package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<IssuerDistinguishedName, IssuerDistinguishedName.IssuerDistinguishedNameBuilder> {

    public IssuerDistinguishedNameTtlvDeserializer() {
        super(IssuerDistinguishedName.kmipTag, IssuerDistinguishedName.encodingType);
    }

    @Override
    protected IssuerDistinguishedName.IssuerDistinguishedNameBuilder createBuilder() {
        return IssuerDistinguishedName.builder();
    }

    @Override
    protected void setValue(IssuerDistinguishedName.IssuerDistinguishedNameBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
    }

    @Override
    protected IssuerDistinguishedName build(IssuerDistinguishedName.IssuerDistinguishedNameBuilder builder) {
        return builder.build();
    }
}
