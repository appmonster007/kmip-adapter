package org.purplebean.kmip.benchmark.subjects.model.v3x0.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.enumeration.OtpAlgorithm;

/**
 * Benchmark subject for {@link OtpAlgorithm}.
 */
public class OtpAlgorithmBenchmarkSubject extends KmipBenchmarkSubject<OtpAlgorithm> {

  /**
   * Constructs a new {@link OtpAlgorithmBenchmarkSubject}.
   */
  public OtpAlgorithmBenchmarkSubject() throws Exception {
    OtpAlgorithm otpAlgorithm = OtpAlgorithm.Standard.HOTP.inst();
    initialize(otpAlgorithm, OtpAlgorithm.class);
  }

  @Override
  public String name() {
    return "OtpAlgorithm";
  }

}
