package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.DeleteAttributeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeleteAttributeOpResponsePayload Xml Serialization Tests")
class DeleteAttributeOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<DeleteAttributeOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purplebean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<DeleteAttributeOpResponsePayload> type() {
    return DeleteAttributeOpResponsePayload.class;
  }

  @Override
  public DeleteAttributeOpResponsePayload createDefault() {
    return DeleteAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public DeleteAttributeOpResponsePayload createVariant() {
    return DeleteAttributeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid-2"))
        .build();
  }
}
