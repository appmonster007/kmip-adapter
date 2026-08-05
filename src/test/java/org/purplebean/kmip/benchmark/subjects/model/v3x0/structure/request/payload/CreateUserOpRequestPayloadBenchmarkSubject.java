package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateUserOpRequestPayload;

/**
 * Benchmark subject for {@link CreateUserOpRequestPayload}.
 */
public class CreateUserOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateUserOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link CreateUserOpRequestPayloadBenchmarkSubject}.
   */
  public CreateUserOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateUserOpRequestPayload subject = CreateUserOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
    initialize(subject, CreateUserOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateUserOpRequestPayload";
  }
}