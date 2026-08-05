package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;

/**
 * Benchmark subject for {@link CertificateIssuerDistinguishedName}.
 */
public class CertificateIssuerDistinguishedNameBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateIssuerDistinguishedName> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateIssuerDistinguishedNameBenchmarkSubject}.
   */
  public CertificateIssuerDistinguishedNameBenchmarkSubject() throws Exception {
    CertificateIssuerDistinguishedName certificateIssuerDistinguishedName =
        CertificateIssuerDistinguishedName.of("CN=Test Issuer");
    initialize(certificateIssuerDistinguishedName, CertificateIssuerDistinguishedName.class);
  }

  @Override
  public String name() {
    return "CertificateIssuerDistinguishedName";
  }

}