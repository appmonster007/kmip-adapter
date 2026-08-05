package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purplebean.kmip.model.v2x1.structure.request.payload.InteropOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.InteropIdentifier;

/**
 * Benchmark subject for {@link InteropOpRequestPayload}.
 */
public class InteropOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<InteropOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link InteropOpRequestPayloadBenchmarkSubject}.
   */
  public InteropOpRequestPayloadBenchmarkSubject() throws Exception {
    InteropOpRequestPayload subject = InteropOpRequestPayload
        .builder()
        .interopFunction(InteropFunction.Standard.BEGIN.inst())
        .interopIdentifier(InteropIdentifier.of("*"))
        .build();
    initialize(subject, InteropOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "InteropOpRequestPayload";
  }
}