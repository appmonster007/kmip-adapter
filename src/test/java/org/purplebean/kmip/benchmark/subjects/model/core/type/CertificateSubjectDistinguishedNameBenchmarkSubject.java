package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateSubjectDistinguishedName;

/**
 * Benchmark subject for {@link CertificateSubjectDistinguishedName}.
 */
public class CertificateSubjectDistinguishedNameBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSubjectDistinguishedName> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateSubjectDistinguishedNameBenchmarkSubject}.
   */
  public CertificateSubjectDistinguishedNameBenchmarkSubject() throws Exception {
    CertificateSubjectDistinguishedName certificateSubjectDistinguishedName =
        CertificateSubjectDistinguishedName
            .builder()
            .value("test-subject-dn")
            .build();
    initialize(certificateSubjectDistinguishedName, CertificateSubjectDistinguishedName.class);
  }

  @Override
  public String name() {
    return "CertificateSubjectDistinguishedName";
  }

}