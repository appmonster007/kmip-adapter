package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.GetAttributeListOpRequestPayload;

public class GetAttributeListOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetAttributeListOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public GetAttributeListOpRequestPayloadBenchmarkSubject() throws Exception {
    GetAttributeListOpRequestPayload subject = GetAttributeListOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
    initialize(subject, GetAttributeListOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "GetAttributeListOpRequestPayload";
  }
}
