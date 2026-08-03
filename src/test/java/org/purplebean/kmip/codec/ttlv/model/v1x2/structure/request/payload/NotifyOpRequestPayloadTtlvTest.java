package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.NotifyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NotifyOpRequestPayload Ttlv Serialization Tests")
class NotifyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<NotifyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<NotifyOpRequestPayload> type() {
    return NotifyOpRequestPayload.class;
  }

  @Override
  public NotifyOpRequestPayload createDefault() {
    return NotifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attribute(Attribute.of(AttributeName.of("test-attribute"),
            AttributeValue.ofTextString("test-value")))
        .build();
  }

  @Override
  public NotifyOpRequestPayload createVariant() {
    return NotifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .attribute(Attribute.of(AttributeName.of("variant-attribute"),
            AttributeValue.ofTextString("variant-value")))
        .build();
  }
}
