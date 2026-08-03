package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.structure.Credential;
import org.purplebean.kmip.model.core.structure.UsernameAndPassword;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;

public class CredentialBenchmarkSubject extends KmipBenchmarkSubject<Credential> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public CredentialBenchmarkSubject() throws Exception {
    Credential subject = Credential
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .credentialValue(UsernameAndPassword
            .builder()
            .username(Username.of("test-user"))
            .password(Password.of("test-password"))
            .build())
        .build();
    initialize(subject, Credential.class);
  }

  @Override
  public String name() {
    return "Credential";
  }

}