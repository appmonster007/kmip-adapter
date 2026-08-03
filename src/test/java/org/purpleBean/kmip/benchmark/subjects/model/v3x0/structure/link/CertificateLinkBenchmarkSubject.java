package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.CertificateLink;

public class CertificateLinkBenchmarkSubject extends KmipBenchmarkSubject<CertificateLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public CertificateLinkBenchmarkSubject() throws Exception {
    CertificateLink subject = CertificateLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, CertificateLink.class);
  }

  @Override
  public String name() {
    return "CertificateLink";
  }
}