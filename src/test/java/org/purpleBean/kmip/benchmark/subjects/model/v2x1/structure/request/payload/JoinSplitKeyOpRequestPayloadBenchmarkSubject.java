package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.JoinSplitKeyOpRequestPayload;

public class JoinSplitKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<JoinSplitKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public JoinSplitKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    JoinSplitKeyOpRequestPayload subject = JoinSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("part-1")
            .build())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("part-2")
            .build())
        .build();
    initialize(subject, JoinSplitKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "JoinSplitKeyOpRequestPayload";
  }
}