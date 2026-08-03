package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v1x2.structure.response.payload.PutOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PutOpResponsePayload Xml Serialization Tests")
class PutOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<PutOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<PutOpResponsePayload> type() {
    return PutOpResponsePayload.class;
  }

  @Override
  public PutOpResponsePayload createDefault() {
    return PutOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public PutOpResponsePayload createVariant() {
    return PutOpResponsePayload
        .builder()
        .build();
  }
}
