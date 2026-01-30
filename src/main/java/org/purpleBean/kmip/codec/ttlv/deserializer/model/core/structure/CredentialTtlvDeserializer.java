package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.CredentialValue;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.core.structure.Credential;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Credential, Credential.CredentialBuilder> {

    public CredentialTtlvDeserializer() {
        super(Credential.kmipTag, Credential.encodingType);
    }

    @Override
    protected Credential.CredentialBuilder createBuilder() {
        return Credential.builder();
    }

    @Override
    protected void setValue(Credential.CredentialBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.CREDENTIAL_TYPE -> {
                CredentialType credentialType = mapper.readValue(p, CredentialType.class);
                builder.credentialType(credentialType);
                mapper.setAttribute("credentialType", credentialType.getDescription());
            }
            case KmipTag.Standard.CREDENTIAL_VALUE ->
                    builder.credentialValue(mapper.readValue(p, CredentialValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Credential build(Credential.CredentialBuilder builder) {
        return builder.build();
    }
}