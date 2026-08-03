package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.InteropFunction;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.InteropOpRequestPayload;

public class InteropOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<InteropOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public InteropOpRequestPayloadBenchmarkSubject() throws Exception {
    InteropOpRequestPayload subject = InteropOpRequestPayload
        .builder()
        .interopFunction(InteropFunction.Standard.BEGIN.inst())
        .build();
    initialize(subject, InteropOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "InteropOpRequestPayload";
  }
}