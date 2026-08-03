package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CertificateType;

public class CertificateTypeBenchmarkSubject extends KmipBenchmarkSubject<CertificateType> {

  public CertificateTypeBenchmarkSubject() throws Exception {
    CertificateType certificateType = CertificateType.Standard.X_509.inst();
    initialize(certificateType, CertificateType.class);
  }

  @Override
  public String name() {
    return "CertificateType";
  }

}
