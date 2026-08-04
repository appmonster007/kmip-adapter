package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ModifyAttributeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ModifyAttributeOpResponsePayload Ttlv Serialization Tests")
class ModifyAttributeOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ModifyAttributeOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purplebean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<ModifyAttributeOpResponsePayload> type() {
    return ModifyAttributeOpResponsePayload.class;
  }

  @Override
  public ModifyAttributeOpResponsePayload createDefault() {
    return ModifyAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public ModifyAttributeOpResponsePayload createVariant() {
    return ModifyAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid-2"))
        .build();
  }
}
