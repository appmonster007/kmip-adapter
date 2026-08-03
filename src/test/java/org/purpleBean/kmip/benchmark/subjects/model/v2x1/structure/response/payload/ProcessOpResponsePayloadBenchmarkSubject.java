package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.ProcessOpResponsePayload;

public class ProcessOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ProcessOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public ProcessOpResponsePayloadBenchmarkSubject() throws Exception {
    ProcessOpResponsePayload subject = ProcessOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(
            org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x01, 0x02}))
        .build();
    initialize(subject, ProcessOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ProcessOpResponsePayload";
  }
}