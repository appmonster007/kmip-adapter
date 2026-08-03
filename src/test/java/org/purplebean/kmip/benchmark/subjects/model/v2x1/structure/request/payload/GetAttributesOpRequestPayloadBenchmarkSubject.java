package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetAttributesOpRequestPayload;

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