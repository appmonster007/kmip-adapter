package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.structure.HashedPasswordCredential;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;
import org.purplebean.kmip.model.v3x0.type.HashedUsernamePassword;

/**
 * Benchmark subject for {@link HashedPasswordCredential}.
 */
public class HashedPasswordCredentialBenchmarkSubject
    extends KmipBenchmarkSubject<HashedPasswordCredential> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link HashedPasswordCredentialBenchmarkSubject}.
   */
  public HashedPasswordCredentialBenchmarkSubject() throws Exception {
    HashedPasswordCredential subject = HashedPasswordCredential
        .builder()
        .hashedUsernamePassword(HashedUsernamePassword.of(new byte[] {0x01, 0x02, 0x03}))
        .hashedPasswordUsername(HashedPasswordUsername.of(new byte[] {0x04, 0x05, 0x06}))
        .build();
    initialize(subject, HashedPasswordCredential.class);
  }

  @Override
  public String name() {
    return "HashedPasswordCredential";
  }
}