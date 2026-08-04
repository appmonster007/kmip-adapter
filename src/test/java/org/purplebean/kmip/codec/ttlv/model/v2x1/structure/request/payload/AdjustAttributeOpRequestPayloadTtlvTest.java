package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.model.v2x1.structure.request.payload.AdjustAttributeOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AdjustAttributeOpRequestPayload Ttlv Serialization Tests")
class AdjustAttributeOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<AdjustAttributeOpRequestPayload> {

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
        .attributeReference(AttributeReference
            .builder()
            .attributeName(AttributeName.of("Usage Limits Count"))
            .build())
        .adjustmentType(AdjustmentType.Standard.INCREMENT.inst())
        .adjustmentValue(AdjustmentValue.ofLongInteger(1L))
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
        .attributeReference(AttributeReference
            .builder()
            .attributeName(AttributeName.of("Usage Limits Count"))
            .build())
        .adjustmentType(AdjustmentType.Standard.DECREMENT.inst())
        .adjustmentValue(AdjustmentValue.ofLongInteger(2L))
        .build();
  }
}
