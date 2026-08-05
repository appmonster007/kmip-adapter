package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.JoinSplitKeyOpRequestPayload;

/**
 * Benchmark subject for {@link JoinSplitKeyOpRequestPayload}.
 */
public class JoinSplitKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<JoinSplitKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link JoinSplitKeyOpRequestPayloadBenchmarkSubject}.
   */
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