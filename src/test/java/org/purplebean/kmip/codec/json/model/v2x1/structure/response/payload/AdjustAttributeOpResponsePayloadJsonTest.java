package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.AdjustAttributeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AdjustAttributeOpResponsePayload Json Serialization Tests")
class AdjustAttributeOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<AdjustAttributeOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<AdjustAttributeOpResponsePayload> type() {
    return AdjustAttributeOpResponsePayload.class;
  }

  @Override
  public AdjustAttributeOpResponsePayload createDefault() {
    return AdjustAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-resp-uid-1")
            .build())
        .build();
  }

  @Override
  public AdjustAttributeOpResponsePayload createVariant() {
    return AdjustAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-resp-uid-2")
            .build())
        .build();
  }
}
