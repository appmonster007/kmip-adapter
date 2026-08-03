package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ActivateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ActivateOpResponsePayload Xml Serialization Tests")
class ActivateOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ActivateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ActivateOpResponsePayload> type() {
    return ActivateOpResponsePayload.class;
  }

  @Override
  public ActivateOpResponsePayload createDefault() {
    return ActivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public ActivateOpResponsePayload createVariant() {
    return ActivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
