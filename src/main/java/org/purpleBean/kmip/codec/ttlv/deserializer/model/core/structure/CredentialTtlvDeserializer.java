package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<Credential, Credential.CredentialBuilder> {

    public CredentialTtlvDeserializer() {
        super(Credential.kmipTag);
    }

    @Override
    protected Credential.CredentialBuilder createBuilder() {
        return Credential.builder();
    }

    @Override
    protected void setValue(Credential.CredentialBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CREDENTIAL_TYPE -> builder.credentialType(mapper.readValue(p, CredentialType.class));
            case KmipTag.Standard.CREDENTIAL_VALUE ->
                    builder.credentialValue(mapper.readValue(p, CredentialValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Credential build(Credential.CredentialBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return Credential.encodingType;
    }
}