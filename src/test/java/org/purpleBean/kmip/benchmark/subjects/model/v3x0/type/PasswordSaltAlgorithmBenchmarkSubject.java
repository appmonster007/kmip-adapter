package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v3x0.type.PasswordSaltAlgorithm;

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