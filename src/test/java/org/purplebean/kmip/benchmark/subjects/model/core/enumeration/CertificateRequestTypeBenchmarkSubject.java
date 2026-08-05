package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CertificateRequestType;

/**
 * Benchmark subject for {@link CertificateRequestType}.
 */
public class CertificateRequestTypeBenchmarkSubject
    extends KmipBenchmarkSubject<CertificateRequestType> {

  /**
   * Constructs a new {@link CertificateRequestTypeBenchmarkSubject}.
   */
  public CertificateRequestTypeBenchmarkSubject() throws Exception {
    CertificateRequestType certificateRequestType = CertificateRequestType.Standard.CRMF.inst();
    initialize(certificateRequestType, CertificateRequestType.class);
  }

  @Override
  public String name() {
    return "CertificateRequestType";
  }

}
