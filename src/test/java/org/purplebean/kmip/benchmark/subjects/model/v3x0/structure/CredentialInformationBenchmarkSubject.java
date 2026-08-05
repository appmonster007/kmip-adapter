package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.v3x0.structure.CredentialInformation;

/**
 * Benchmark subject for {@link CredentialInformation}.
 */
public class CredentialInformationBenchmarkSubject
    extends KmipBenchmarkSubject<CredentialInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link CredentialInformationBenchmarkSubject}.
   */
  public CredentialInformationBenchmarkSubject() throws Exception {
    CredentialInformation subject = CredentialInformation
        .builder()
        .credentialType(CredentialType.of(CredentialType.Standard.USERNAME_AND_PASSWORD))
        .build();
    initialize(subject, CredentialInformation.class);
  }

  @Override
  public String name() {
    return "CredentialInformation";
  }
}