package org.purpleBean.kmip.codec.xml.model.v3x0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.DeactivateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeactivateOpResponsePayload Xml Serialization Tests")
class DeactivateOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<DeactivateOpResponsePayload> {

  @Override
  public Class<DeactivateOpResponsePayload> type() {
    return DeactivateOpResponsePayload.class;
  }

  @Override
  public DeactivateOpResponsePayload createDefault() {
    return DeactivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public DeactivateOpResponsePayload createVariant() {
    return DeactivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}