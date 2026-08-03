package org.purpleBean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.RecoverOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RecoverOpRequestPayload Xml Serialization Tests")
class RecoverOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RecoverOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RecoverOpRequestPayload> type() {
    return RecoverOpRequestPayload.class;
  }

  @Override
  public RecoverOpRequestPayload createDefault() {
    return RecoverOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public RecoverOpRequestPayload createVariant() {
    return RecoverOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
