package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ExportOpRequestPayload;

/**
 * Benchmark subject for {@link ExportOpRequestPayload}.
 */
public class ExportOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ExportOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  /**
   * Constructs a new {@link ExportOpRequestPayloadBenchmarkSubject}.
   */
  public ExportOpRequestPayloadBenchmarkSubject() throws Exception {
    ExportOpRequestPayload subject = ExportOpRequestPayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, ExportOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ExportOpRequestPayload";
  }
}