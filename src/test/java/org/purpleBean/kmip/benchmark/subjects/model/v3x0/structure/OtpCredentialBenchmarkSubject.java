package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.enumeration.OtpAlgorithm;
import org.purpleBean.kmip.model.v3x0.structure.OtpCredential;

public class OtpCredentialBenchmarkSubject extends KmipBenchmarkSubject<OtpCredential> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

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