package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.structure.Authentication;
import org.purplebean.kmip.model.core.structure.Credential;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;

/**
 * Benchmark subject for {@link Authentication}.
 */
public class AuthenticationBenchmarkSubject extends KmipBenchmarkSubject<Authentication> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AuthenticationBenchmarkSubject}.
   */
  public AuthenticationBenchmarkSubject() throws Exception {
    Credential credential = Credential
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .credentialValue(UsernameAndPassword
            .builder()
            .username(Username.of("test-user"))
            .password(Password.of("test-password"))
            .build())
        .build();
    Authentication subject = Authentication
        .builder()
        .credential(credential)
        .credential(credential)
        .build();
    initialize(subject, Authentication.class);
  }

  @Override
  public String name() {
    return "Authentication";
  }

}