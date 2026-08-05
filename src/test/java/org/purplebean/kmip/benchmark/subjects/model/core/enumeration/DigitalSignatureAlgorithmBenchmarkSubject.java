package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

/**
 * Benchmark subject for {@link DigitalSignatureAlgorithm}.
 */
public class DigitalSignatureAlgorithmBenchmarkSubject
    extends KmipBenchmarkSubject<DigitalSignatureAlgorithm> {

  /**
   * Constructs a new {@link DigitalSignatureAlgorithmBenchmarkSubject}.
   */
  public DigitalSignatureAlgorithmBenchmarkSubject() throws Exception {
    DigitalSignatureAlgorithm digitalSignatureAlgorithm =
        DigitalSignatureAlgorithm.Standard.MD2_WITH_RSA_ENCRYPTION.inst();
    initialize(digitalSignatureAlgorithm, DigitalSignatureAlgorithm.class);
  }

  @Override
  public String name() {
    return "DigitalSignatureAlgorithm";
  }

}
