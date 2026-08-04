package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ModifyAttributeOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ModifyAttributeOpRequestPayload Json Serialization Tests")
class ModifyAttributeOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ModifyAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purplebean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<ModifyAttributeOpRequestPayload> type() {
    return ModifyAttributeOpRequestPayload.class;
  }

  @Override
  public ModifyAttributeOpRequestPayload createDefault() {
    return ModifyAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
  }

  @Override
  public ModifyAttributeOpRequestPayload createVariant() {
    return ModifyAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid-2"))
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
            .build())
        .build();
  }
}
