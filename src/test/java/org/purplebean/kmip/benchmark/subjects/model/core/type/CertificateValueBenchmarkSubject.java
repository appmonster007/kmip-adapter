package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateValue;

/**
 * Benchmark subject for {@link CertificateValue}.
 */
public class CertificateValueBenchmarkSubject extends KmipBenchmarkSubject<CertificateValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CertificateValueBenchmarkSubject}.
   */
  public CertificateValueBenchmarkSubject() throws Exception {
    CertificateValue certificateValue = CertificateValue.of(new byte[] {0x01, 0x02, 0x03});
    initialize(certificateValue, CertificateValue.class);
  }

  @Override
  public String name() {
    return "CertificateValue";
  }

}