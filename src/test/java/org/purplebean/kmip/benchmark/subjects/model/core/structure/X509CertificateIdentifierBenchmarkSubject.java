package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.X509CertificateIdentifier;
import org.purplebean.kmip.model.core.type.CertificateSerialNumber;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

public class X509CertificateIdentifierBenchmarkSubject
    extends KmipBenchmarkSubject<X509CertificateIdentifier> {

  public X509CertificateIdentifierBenchmarkSubject() throws Exception {
    X509CertificateIdentifier x509CertificateIdentifier = X509CertificateIdentifier
        .builder()
        .issuerDistinguishedName(IssuerDistinguishedName.of("test-issuer".getBytes()))
        .certificateSerialNumber(CertificateSerialNumber.of("12345".getBytes()))
        .build();
    initialize(x509CertificateIdentifier, X509CertificateIdentifier.class);
  }

  @Override
  public String name() {
    return "X509CertificateIdentifier";
  }

}
