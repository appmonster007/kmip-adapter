package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;

/**
 * Benchmark subject for {@link UsernameAndPassword}.
 */
public class UsernameAndPasswordBenchmarkSubject extends KmipBenchmarkSubject<UsernameAndPassword> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link UsernameAndPasswordBenchmarkSubject}.
   */
  public UsernameAndPasswordBenchmarkSubject() throws Exception {
    UsernameAndPassword subject = UsernameAndPassword
        .builder()
        .username(Username.of("test-user"))
        .password(Password.of("test-password"))
        .build();
    initialize(subject, UsernameAndPassword.class);
  }

  @Override
  public String name() {
    return "UsernameAndPassword";
  }

}