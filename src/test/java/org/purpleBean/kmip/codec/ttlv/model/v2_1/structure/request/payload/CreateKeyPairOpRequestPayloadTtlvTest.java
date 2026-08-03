package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateKeyPairOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateKeyPairOpRequestPayload Ttlv Serialization Tests")
class CreateKeyPairOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CreateKeyPairOpRequestPayload> {

  @Override
  public Class<CreateKeyPairOpRequestPayload> type() {
    return CreateKeyPairOpRequestPayload.class;
  }

  @Override
  public CreateKeyPairOpRequestPayload createDefault() {
    return CreateKeyPairOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public CreateKeyPairOpRequestPayload createVariant() {
    return CreateKeyPairOpRequestPayload
        .builder()
        .build();
  }
}