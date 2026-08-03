package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.GetAttributesOpRequestPayload;

public class GetAttributesOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetAttributesOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public GetAttributesOpRequestPayloadBenchmarkSubject() throws Exception {
    GetAttributesOpRequestPayload subject = GetAttributesOpRequestPayload
        .builder()
        .build();
    initialize(subject, GetAttributesOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "GetAttributesOpRequestPayload";
  }
}