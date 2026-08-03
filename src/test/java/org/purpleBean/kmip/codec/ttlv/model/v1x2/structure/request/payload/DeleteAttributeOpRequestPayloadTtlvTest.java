package org.purpleBean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.DeleteAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeleteAttributeOpRequestPayload Ttlv Serialization Tests")
class DeleteAttributeOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DeleteAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DeleteAttributeOpRequestPayload> type() {
    return DeleteAttributeOpRequestPayload.class;
  }

  @Override
  public DeleteAttributeOpRequestPayload createDefault() {
    return DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attributeName(AttributeName.of("test-attribute"))
        .attributeIndex(AttributeIndex.of(1))
        .build();
  }

  @Override
  public DeleteAttributeOpRequestPayload createVariant() {
    return DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .attributeName(AttributeName.of("variant-attribute"))
        .attributeIndex(AttributeIndex.of(2))
        .build();
  }
}
