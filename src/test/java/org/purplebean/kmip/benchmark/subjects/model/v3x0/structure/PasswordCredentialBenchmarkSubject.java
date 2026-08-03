package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.v3x0.structure.PasswordCredential;

public class PasswordCredentialBenchmarkSubject extends KmipBenchmarkSubject<PasswordCredential> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public PasswordCredentialBenchmarkSubject() throws Exception {
    PasswordCredential subject = PasswordCredential
        .builder()
        .password(Password.of("s3cr3t"))
        .build();
    initialize(subject, PasswordCredential.class);
  }

  @Override
  public String name() {
    return "PasswordCredential";
  }
}