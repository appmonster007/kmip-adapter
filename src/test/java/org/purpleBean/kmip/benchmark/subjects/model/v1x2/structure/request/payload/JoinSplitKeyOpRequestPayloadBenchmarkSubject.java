package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.JoinSplitKeyOpRequestPayload;

public class JoinSplitKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<JoinSplitKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public JoinSplitKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    JoinSplitKeyOpRequestPayload subject = JoinSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
    initialize(subject, JoinSplitKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "JoinSplitKeyOpRequestPayload";
  }
}
