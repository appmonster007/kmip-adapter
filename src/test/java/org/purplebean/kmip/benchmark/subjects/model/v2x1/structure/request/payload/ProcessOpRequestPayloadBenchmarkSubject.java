package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ProcessOpRequestPayload;

/**
 * Benchmark subject for {@link ProcessOpRequestPayload}.
 */
public class ProcessOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ProcessOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link ProcessOpRequestPayloadBenchmarkSubject}.
   */
  public ProcessOpRequestPayloadBenchmarkSubject() throws Exception {
    ProcessOpRequestPayload subject = ProcessOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(
            org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x01, 0x02}))
        .build();
    initialize(subject, ProcessOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ProcessOpRequestPayload";
  }
}