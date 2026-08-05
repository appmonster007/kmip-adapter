package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CertificateRequest;

/**
 * Benchmark subject for {@link CertificateRequest}.
 */
public class CertificateRequestBenchmarkSubject extends KmipBenchmarkSubject<CertificateRequest> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CertificateRequestBenchmarkSubject}.
   */
  public CertificateRequestBenchmarkSubject() throws Exception {
    CertificateRequest certificateRequest = CertificateRequest.of(new byte[] {0x01, 0x02, 0x03});
    initialize(certificateRequest, CertificateRequest.class);
  }

  @Override
  public String name() {
    return "CertificateRequest";
  }

}