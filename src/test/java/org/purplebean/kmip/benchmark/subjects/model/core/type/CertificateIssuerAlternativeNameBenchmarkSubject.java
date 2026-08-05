package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateIssuerAlternativeName;

/**
 * Benchmark subject for {@link CertificateIssuerAlternativeName}.
 */
public class CertificateIssuerAlternativeNameBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateIssuerAlternativeName> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateIssuerAlternativeNameBenchmarkSubject}.
   */
  public CertificateIssuerAlternativeNameBenchmarkSubject() throws Exception {
    CertificateIssuerAlternativeName certificateIssuerAlternativeName =
        CertificateIssuerAlternativeName
            .builder()
            .value("test-issuer-alt-name")
            .build();
    initialize(certificateIssuerAlternativeName, CertificateIssuerAlternativeName.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerAlternativeName";
  }

}