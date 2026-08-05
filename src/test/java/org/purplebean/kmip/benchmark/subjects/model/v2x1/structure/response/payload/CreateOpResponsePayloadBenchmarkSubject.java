package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.CreateOpResponsePayload;

/**
 * Benchmark subject for {@link CreateOpResponsePayload}.
 */
public class CreateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link CreateOpResponsePayloadBenchmarkSubject}.
   */
  public CreateOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateOpResponsePayload subject = CreateOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
    initialize(subject, CreateOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateOpResponsePayload";
  }
}
