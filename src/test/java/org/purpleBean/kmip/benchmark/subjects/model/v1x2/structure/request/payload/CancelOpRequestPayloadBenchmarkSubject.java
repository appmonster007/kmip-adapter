package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CancelOpRequestPayload;

public class CancelOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CancelOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CancelOpRequestPayloadBenchmarkSubject() throws Exception {
    CancelOpRequestPayload subject = CancelOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, CancelOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CancelOpRequestPayload";
  }
}
