package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetAttributeListOpResponsePayload;

public class GetAttributeListOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetAttributeListOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public GetAttributeListOpResponsePayloadBenchmarkSubject() throws Exception {
    GetAttributeListOpResponsePayload subject = GetAttributeListOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attributeName(AttributeName.of("Attribute1"))
        .attributeName(AttributeName.of("Attribute2"))
        .build();
    initialize(subject, GetAttributeListOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetAttributeListOpResponsePayload";
  }
}
