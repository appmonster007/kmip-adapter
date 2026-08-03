package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CreateSplitKeyOpResponsePayload;

public class CreateSplitKeyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateSplitKeyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CreateSplitKeyOpResponsePayloadBenchmarkSubject() throws Exception {
    CreateSplitKeyOpResponsePayload subject = CreateSplitKeyOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
    initialize(subject, CreateSplitKeyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CreateSplitKeyOpResponsePayload";
  }
}
