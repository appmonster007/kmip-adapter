package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.AddAttributeOpRequestPayload;

public class AddAttributeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<AddAttributeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public AddAttributeOpRequestPayloadBenchmarkSubject() throws Exception {
    AddAttributeOpRequestPayload subject = AddAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
    initialize(subject, AddAttributeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "AddAttributeOpRequestPayload";
  }
}
