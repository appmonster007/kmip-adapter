package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CredentialType, CredentialType.CredentialTypeBuilder> {

    public CredentialTypeTtlvDeserializer() {
        super(CredentialType.kmipTag, CredentialType.encodingType);
    }

    @Override
    protected CredentialType.CredentialTypeBuilder createBuilder() {
        return CredentialType.builder();
    }

    @Override
    protected void setValue(CredentialType.CredentialTypeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(CredentialType.fromValue(value));
    }

    @Override
    protected CredentialType build(CredentialType.CredentialTypeBuilder builder) {
        return builder.build();
    }
}
