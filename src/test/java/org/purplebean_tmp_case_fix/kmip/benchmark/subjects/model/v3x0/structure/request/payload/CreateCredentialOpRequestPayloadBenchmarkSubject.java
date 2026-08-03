package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.core.type.Username;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateCredentialOpRequestPayload;

public class CreateCredentialOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateCredentialOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public CreateCredentialOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateCredentialOpRequestPayload subject = CreateCredentialOpRequestPayload
        .builder()
        .credentialType(CredentialType.Standard.USERNAME_AND_PASSWORD.inst())
        .credentialValue(
            org.purplebean.kmip.model.core.structure.UsernameAndPassword.of(Username.of("test"),
                Password.of("pass")))
        .build();
    initialize(subject, CreateCredentialOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateCredentialOpRequestPayload";
  }
}