package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateSerialNumber;

/**
 * Benchmark subject for {@link CertificateSerialNumber}.
 */
public class CertificateSerialNumberBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSerialNumber> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CertificateSerialNumberBenchmarkSubject}.
   */
  public CertificateSerialNumberBenchmarkSubject() throws Exception {
    CertificateSerialNumber certificateSerialNumber =
        CertificateSerialNumber.of("12345".getBytes());
    initialize(certificateSerialNumber, CertificateSerialNumber.class);
  }

  @Override
  public String name() {
    return "CertificateSerialNumber";
  }

}
