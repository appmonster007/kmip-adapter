package org.purpleBean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.v3x0.type.OtpDigest;

public class OtpDigestBenchmarkSubject extends KmipBenchmarkSubject<OtpDigest> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

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