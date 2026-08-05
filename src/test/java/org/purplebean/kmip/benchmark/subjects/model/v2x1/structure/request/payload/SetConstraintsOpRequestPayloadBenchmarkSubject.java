package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Constraints;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetConstraintsOpRequestPayload;

/**
 * Benchmark subject for {@link SetConstraintsOpRequestPayload}.
 */
public class SetConstraintsOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetConstraintsOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link SetConstraintsOpRequestPayloadBenchmarkSubject}.
   */
  public SetConstraintsOpRequestPayloadBenchmarkSubject() throws Exception {
    SetConstraintsOpRequestPayload subject = SetConstraintsOpRequestPayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
    initialize(subject, SetConstraintsOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SetConstraintsOpRequestPayload";
  }
}