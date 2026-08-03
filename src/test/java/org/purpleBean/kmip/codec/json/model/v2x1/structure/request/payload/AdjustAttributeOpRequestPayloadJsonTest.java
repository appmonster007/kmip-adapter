package org.purpleBean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purpleBean.kmip.model.v2x1.structure.CurrentAttribute;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.AdjustAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AdjustAttributeOpRequestPayload Json Serialization Tests")
class AdjustAttributeOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<AdjustAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<AdjustAttributeOpRequestPayload> type() {
    return AdjustAttributeOpRequestPayload.class;
  }

  @Override
  public AdjustAttributeOpRequestPayload createDefault() {
    return AdjustAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-uid-1")
            .build())
        .currentAttribute(CurrentAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
        .build();
  }

  @Override
  public AdjustAttributeOpRequestPayload createVariant() {
    return AdjustAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("adj-attr-uid-2")
            .build())
        .currentAttribute(CurrentAttribute
            .builder()
            .attribute(CryptographicAlgorithm.Standard.RSA.inst())
            .build())
        .adjustmentType(AdjustmentType.Standard.DECREMENT.inst())
        .build();
  }
}
