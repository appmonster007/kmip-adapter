package org.purpleBean.kmip.benchmark.subjects.model.v3_0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;

public class PasswordSaltAlgorithmBenchmarkSubject
    extends KmipBenchmarkSubject<PasswordSaltAlgorithm> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0; // TODO: Adjust if needed

  public PasswordSaltAlgorithmBenchmarkSubject() throws Exception {
    PasswordSaltAlgorithm subject = PasswordSaltAlgorithm.of(
        CryptographicAlgorithm.Standard.AES);  // TODO: Create a default instance
    initialize(subject, PasswordSaltAlgorithm.class);
  }

  @Override
  public String name() {
    return "PasswordSaltAlgorithm";
  }
}