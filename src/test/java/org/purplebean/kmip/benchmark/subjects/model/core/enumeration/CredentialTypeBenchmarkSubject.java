package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CredentialType;

/**
 * Benchmark subject for {@link CredentialType}.
 */
public class CredentialTypeBenchmarkSubject extends KmipBenchmarkSubject<CredentialType> {

  /**
   * Constructs a new {@link CredentialTypeBenchmarkSubject}.
   */
  public CredentialTypeBenchmarkSubject() throws Exception {
    CredentialType credentialType = CredentialType.Standard.USERNAME_AND_PASSWORD.inst();
    initialize(credentialType, CredentialType.class);
  }

  @Override
  public String name() {
    return "CredentialType";
  }

}
