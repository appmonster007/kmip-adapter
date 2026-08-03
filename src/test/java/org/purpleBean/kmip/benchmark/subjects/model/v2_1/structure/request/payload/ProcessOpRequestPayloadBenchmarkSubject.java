package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ProcessOpRequestPayload;

public class ProcessOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ProcessOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public ProcessOpRequestPayloadBenchmarkSubject() throws Exception {
    ProcessOpRequestPayload subject = ProcessOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(
            org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x01, 0x02}))
        .build();
    initialize(subject, ProcessOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ProcessOpRequestPayload";
  }
}