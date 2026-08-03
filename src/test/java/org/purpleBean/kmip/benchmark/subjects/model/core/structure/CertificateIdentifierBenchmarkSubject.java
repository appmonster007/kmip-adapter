package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.CertificateIdentifier;
import org.purpleBean.kmip.model.core.type.Issuer;
import org.purpleBean.kmip.model.core.type.SerialNumber;

public class CertificateIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateIdentifier> {

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