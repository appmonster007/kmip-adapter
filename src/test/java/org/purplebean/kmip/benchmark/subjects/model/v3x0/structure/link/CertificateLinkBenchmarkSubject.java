package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.CertificateLink;

/**
 * Benchmark subject for {@link CertificateLink}.
 */
public class CertificateLinkBenchmarkSubject extends KmipBenchmarkSubject<CertificateLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link CertificateLinkBenchmarkSubject}.
   */
  public CertificateLinkBenchmarkSubject() throws Exception {
    CertificateLink subject = CertificateLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, CertificateLink.class);
  }

  @Override
  public String name() {
    return "CertificateLink";
  }
}