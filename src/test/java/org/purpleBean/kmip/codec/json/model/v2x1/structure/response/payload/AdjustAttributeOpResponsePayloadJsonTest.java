package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.AdjustAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
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
        .newAttribute(NewAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.RSA.inst())
            .build())
        .build();
  }
}
