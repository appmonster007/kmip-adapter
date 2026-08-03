package org.purpleBean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.DestroyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DestroyOpResponsePayload Xml Serialization Tests")
class DestroyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<DestroyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DestroyOpResponsePayload> type() {
    return DestroyOpResponsePayload.class;
  }

  @Override
  public DestroyOpResponsePayload createDefault() {
    return DestroyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public DestroyOpResponsePayload createVariant() {
    return DestroyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
