package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v3x0.type.OtpDigest;

/**
 * Benchmark subject for {@link OtpDigest}.
 */
public class OtpDigestBenchmarkSubject extends KmipBenchmarkSubject<OtpDigest> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  /**
   * Constructs a new {@link OtpDigestBenchmarkSubject}.
   */
  public OtpDigestBenchmarkSubject() throws Exception {
    OtpDigest subject =
        OtpDigest.of(CryptographicAlgorithm.Standard.AES);  // TODO: Create a default instance
    initialize(subject, OtpDigest.class);
  }

  @Override
  public String name() {
    return "OtpDigest";
  }
}