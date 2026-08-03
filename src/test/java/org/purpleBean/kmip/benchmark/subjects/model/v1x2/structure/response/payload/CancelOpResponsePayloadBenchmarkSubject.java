package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.CancelOpResponsePayload;

public class CancelOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CancelOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CancelOpResponsePayloadBenchmarkSubject() throws Exception {
    CancelOpResponsePayload subject = CancelOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[] {1, 2, 3}))
        .cancellationResult(CancellationResult.of(CancellationResult.Standard.CANCELED))
        .build();
    initialize(subject, CancelOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CancelOpResponsePayload";
  }
}
