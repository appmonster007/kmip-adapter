package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CertificateSerialNumber;

public class CertificateSerialNumberBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateSerialNumber> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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
