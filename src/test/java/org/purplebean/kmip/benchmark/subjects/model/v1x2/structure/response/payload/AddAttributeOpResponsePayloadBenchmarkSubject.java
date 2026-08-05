package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.AddAttributeOpResponsePayload;

/**
 * Benchmark subject for {@link AddAttributeOpResponsePayload}.
 */
public class AddAttributeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<AddAttributeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link AddAttributeOpResponsePayloadBenchmarkSubject}.
   */
  public AddAttributeOpResponsePayloadBenchmarkSubject() throws Exception {
    AddAttributeOpResponsePayload subject = AddAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
    initialize(subject, AddAttributeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "AddAttributeOpResponsePayload";
  }
}
