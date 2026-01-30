package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.AttestationCredential;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttestationCredentialTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationCredential, AttestationCredential.AttestationCredentialBuilder> {

    public AttestationCredentialTtlvDeserializer() {
        super(AttestationCredential.kmipTag, AttestationCredential.encodingType);
    }

    @Override
    protected AttestationCredential.AttestationCredentialBuilder createBuilder() {
        return AttestationCredential.builder();
    }

    @Override
    protected void setValue(AttestationCredential.AttestationCredentialBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.NONCE -> builder.nonce(mapper.readValue(p, Nonce.class));
            case KmipTag.Standard.ATTESTATION_TYPE ->
                    builder.attestationType(mapper.readValue(p, AttestationType.class));
            case KmipTag.Standard.ATTESTATION_MEASUREMENT ->
                    builder.attestationMeasurement(mapper.readValue(p, AttestationMeasurement.class));
            case KmipTag.Standard.ATTESTATION_ASSERTION ->
                    builder.attestationAssertion(mapper.readValue(p, AttestationAssertion.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected AttestationCredential build(AttestationCredential.AttestationCredentialBuilder builder) {
        return builder.build();
    }
}