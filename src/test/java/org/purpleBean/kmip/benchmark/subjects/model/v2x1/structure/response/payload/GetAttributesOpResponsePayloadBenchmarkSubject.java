package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import java.util.List;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.GetAttributesOpResponsePayload;

public class GetAttributesOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetAttributesOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public GetAttributesOpResponsePayloadBenchmarkSubject() throws Exception {
    GetAttributesOpResponsePayload subject = GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributes(Attributes.of(List.of()))
        .build();
    initialize(subject, GetAttributesOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetAttributesOpResponsePayload";
  }
}
