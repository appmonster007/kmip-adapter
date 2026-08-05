package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ProcessOpResponsePayload;

/**
 * Benchmark subject for {@link ProcessOpResponsePayload}.
 */
public class ProcessOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ProcessOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link ProcessOpResponsePayloadBenchmarkSubject}.
   */
  public ProcessOpResponsePayloadBenchmarkSubject() throws Exception {
    ProcessOpResponsePayload subject = ProcessOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(
            org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x01, 0x02}))
        .build();
    initialize(subject, ProcessOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ProcessOpResponsePayload";
  }
}