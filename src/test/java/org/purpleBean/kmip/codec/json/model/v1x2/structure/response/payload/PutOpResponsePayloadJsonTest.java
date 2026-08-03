package org.purpleBean.kmip.codec.json.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.PutOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PutOpResponsePayload Json Serialization Tests")
class PutOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<PutOpResponsePayload> {

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
