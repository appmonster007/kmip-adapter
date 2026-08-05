package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v3x0.structure.request.payload.CreateGroupOpRequestPayload;

/**
 * Benchmark subject for {@link CreateGroupOpRequestPayload}.
 */
public class CreateGroupOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateGroupOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link CreateGroupOpRequestPayloadBenchmarkSubject}.
   */
  public CreateGroupOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateGroupOpRequestPayload subject = CreateGroupOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
    initialize(subject, CreateGroupOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateGroupOpRequestPayload";
  }
}