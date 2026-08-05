package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CancelOpResponsePayload;

/**
 * Benchmark subject for {@link CancelOpResponsePayload}.
 */
public class CancelOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CancelOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CancelOpResponsePayloadBenchmarkSubject}.
   */
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
