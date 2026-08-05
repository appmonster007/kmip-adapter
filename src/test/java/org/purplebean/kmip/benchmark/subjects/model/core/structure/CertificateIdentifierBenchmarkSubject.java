package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.CertificateIdentifier;
import org.purplebean.kmip.model.core.type.Issuer;
import org.purplebean.kmip.model.core.type.SerialNumber;

/**
 * Benchmark subject for {@link CertificateIdentifier}.
 */
public class CertificateIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateIdentifier> {

  /**
   * Constructs a new {@link CertificateIdentifierBenchmarkSubject}.
   */
  public CertificateIdentifierBenchmarkSubject() throws Exception {
    CertificateIdentifier certificateIdentifier = CertificateIdentifier
        .builder()
        .issuer(Issuer.of("CN=Test Issuer"))
        .serialNumber(SerialNumber.of("12345"))
        .build();
    initialize(certificateIdentifier, CertificateIdentifier.class);
  }

  @Override
  public String name() {
    return "CertificateIdentifier";
  }

}