package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.GetAttributesOpResponsePayload;

public class GetAttributesOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetAttributesOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public GetAttributesOpResponsePayloadBenchmarkSubject() throws Exception {
    GetAttributesOpResponsePayload subject = GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(
            Attribute.of(AttributeName.of("Attribute1"), AttributeValue.ofTextString("Value1")))
        .attribute(
            Attribute.of(AttributeName.of("Attribute2"), AttributeValue.ofTextString("Value2")))
        .build();
    initialize(subject, GetAttributesOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetAttributesOpResponsePayload";
  }
}
