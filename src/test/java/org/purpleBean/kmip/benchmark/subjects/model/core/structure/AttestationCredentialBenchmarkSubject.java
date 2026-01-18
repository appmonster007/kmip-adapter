package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.AttestationCredential;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.model.core.type.NonceValue;

public class AttestationCredentialBenchmarkSubject extends KmipBenchmarkSubject<AttestationCredential> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public AttestationCredentialBenchmarkSubject() throws Exception {
        AttestationCredential subject = AttestationCredential.builder()
                .nonce(Nonce.of(
                        NonceId.of(new byte[8]),
                        NonceValue.of(new byte[16])
                ))
                .attestationType(AttestationType.Standard.TPM_QUOTE.inst())
                .build();
        initialize(subject, AttestationCredential.class);
    }

    @Override
    public String name() {
        return "AttestationCredential";
    }

}