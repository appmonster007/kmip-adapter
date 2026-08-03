package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CreateSplitKeyOpRequestPayload;

public class CreateSplitKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CreateSplitKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public CreateSplitKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    CreateSplitKeyOpRequestPayload subject = CreateSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("source-key-id")
            .build())
        .splitKeyParts(SplitKeyParts.of(3))
        .splitKeyThreshold(SplitKeyThreshold.of(2))
        .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
        .build();
    initialize(subject, CreateSplitKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CreateSplitKeyOpRequestPayload";
  }
}