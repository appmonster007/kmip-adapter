package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DeleteAttributeOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeleteAttributeOpRequestPayload Json Serialization Tests")
class DeleteAttributeOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<DeleteAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purplebean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<DeleteAttributeOpRequestPayload> type() {
    return DeleteAttributeOpRequestPayload.class;
  }

  @Override
  public DeleteAttributeOpRequestPayload createDefault() {
    return DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributeReference(AttributeReference
            .builder()
            .attributeName(AttributeName.of("Contact Information"))
            .build())
        .build();
  }

  @Override
  public DeleteAttributeOpRequestPayload createVariant() {
    return DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid-2"))
        .attributeReference(AttributeReference
            .builder()
            .attributeName(AttributeName.of("x-Custom-Attribute"))
            .build())
        .build();
  }
}
