package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.AttestationType;
import org.purplebean.kmip.model.core.structure.AttestationCredential;
import org.purplebean.kmip.model.core.structure.Nonce;
import org.purplebean.kmip.model.core.type.NonceId;
import org.purplebean.kmip.model.core.type.NonceValue;

/**
 * Benchmark subject for {@link AttestationCredential}.
 */
public class AttestationCredentialBenchmarkSubject
    extends KmipBenchmarkSubject<AttestationCredential> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AttestationCredentialBenchmarkSubject}.
   */
  public AttestationCredentialBenchmarkSubject() throws Exception {
    AttestationCredential subject = AttestationCredential
        .builder()
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