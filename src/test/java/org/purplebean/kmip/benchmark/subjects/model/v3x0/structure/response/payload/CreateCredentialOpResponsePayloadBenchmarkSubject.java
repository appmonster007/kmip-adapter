package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.CreateCredentialOpResponsePayload;

/**
 * Benchmark subject for {@link CreateCredentialOpResponsePayload}.
 */
public class CreateCredentialOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateCredentialOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link CreateCredentialOpResponsePayloadBenchmarkSubject}.
   */
  public CreateCredentialOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateCredentialOpResponsePayload subject = CreateCredentialOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
    initialize(subject, CreateCredentialOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateCredentialOpResponsePayload";
  }
}