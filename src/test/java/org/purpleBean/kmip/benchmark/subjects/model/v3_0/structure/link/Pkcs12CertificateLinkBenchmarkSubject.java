package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.Pkcs12CertificateLink;

public class Pkcs12CertificateLinkBenchmarkSubject
    extends KmipBenchmarkSubject<Pkcs12CertificateLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public Pkcs12CertificateLinkBenchmarkSubject() throws Exception {
    Pkcs12CertificateLink subject = Pkcs12CertificateLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, Pkcs12CertificateLink.class);
  }

  @Override
  public String name() {
    return "Pkcs12CertificateLink";
  }
}