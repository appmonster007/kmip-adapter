package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateLength;

public class CertificateLengthBenchmarkSubject extends KmipBenchmarkSubject<CertificateLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CertificateLengthBenchmarkSubject() throws Exception {
    Integer value = 10;
    CertificateLength certificateLength = CertificateLength
        .builder()
        .value(value)
        .build();
    initialize(certificateLength, CertificateLength.class);
  }

  @Override
  public String name() {
    return "CertificateLength";
  }

}
