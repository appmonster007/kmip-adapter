package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.ExportOpRequestPayload;

public class ExportOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ExportOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

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