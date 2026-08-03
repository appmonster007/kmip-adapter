package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AddAttributeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AddAttributeOpResponsePayload Ttlv Serialization Tests")
class AddAttributeOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<AddAttributeOpResponsePayload> {

  @Override
  public Class<AddAttributeOpResponsePayload> type() {
    return AddAttributeOpResponsePayload.class;
  }

  @Override
  public AddAttributeOpResponsePayload createDefault() {
    return AddAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public AddAttributeOpResponsePayload createVariant() {
    return AddAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("other-uid"))
        .build();
  }
}