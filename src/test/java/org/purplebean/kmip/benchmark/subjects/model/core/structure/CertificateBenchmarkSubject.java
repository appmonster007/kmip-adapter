package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CertificateType;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * Benchmark subject for {@link Certificate}.
 */
public class CertificateBenchmarkSubject extends KmipBenchmarkSubject<Certificate> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link CertificateBenchmarkSubject}.
   */
  public CertificateBenchmarkSubject() throws Exception {
    Certificate subject = Certificate
        .builder()
        .certificateType(CertificateType.Standard.X_509.inst())
        .certificateValue(CertificateValue.of(new byte[0]))
        .build();
    initialize(subject, Certificate.class);
  }

  @Override
  public String name() {
    return "Certificate";
  }

}