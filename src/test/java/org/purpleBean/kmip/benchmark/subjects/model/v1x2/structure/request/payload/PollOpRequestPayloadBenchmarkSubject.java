package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.request.payload.PollOpRequestPayload;

public class PollOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<PollOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PollOpRequestPayloadBenchmarkSubject() throws Exception {
    PollOpRequestPayload subject = PollOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, PollOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "PollOpRequestPayload";
  }
}
