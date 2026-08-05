package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.enumeration.OtpAlgorithm;
import org.purplebean.kmip.model.v3x0.structure.OtpCredential;

/**
 * Benchmark subject for {@link OtpCredential}.
 */
public class OtpCredentialBenchmarkSubject extends KmipBenchmarkSubject<OtpCredential> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link OtpCredentialBenchmarkSubject}.
   */
  public OtpCredentialBenchmarkSubject() throws Exception {
    OtpCredential subject = OtpCredential
        .builder()
        .otpAlgorithm(OtpAlgorithm.of(OtpAlgorithm.Standard.TOTP))
        .build();
    initialize(subject, OtpCredential.class);
  }

  @Override
  public String name() {
    return "OtpCredential";
  }
}