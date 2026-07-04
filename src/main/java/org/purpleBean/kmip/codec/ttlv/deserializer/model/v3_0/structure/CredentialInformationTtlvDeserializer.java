package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.structure.CredentialInformation;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CredentialInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CredentialInformation, CredentialInformation.CredentialInformationBuilder> {

    public CredentialInformationTtlvDeserializer() {
        super(CredentialInformation.kmipTag, CredentialInformation.encodingType);
    }

    @Override
    protected CredentialInformation.CredentialInformationBuilder createBuilder() {
        return CredentialInformation.builder();
    }

    @Override
    protected void setValue(CredentialInformation.CredentialInformationBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        if (nodeTag == CredentialType.kmipTag.getValue()) {
            builder.credentialType(mapper.readValue(p, CredentialType.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CredentialInformation build(CredentialInformation.CredentialInformationBuilder builder) {
        return builder.build();
    }
}