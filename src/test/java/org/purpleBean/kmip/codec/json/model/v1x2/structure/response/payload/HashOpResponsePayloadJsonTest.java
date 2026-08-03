package org.purpleBean.kmip.codec.json.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.HashOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("HashOpResponsePayload Json Serialization Tests")
class HashOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<HashOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<HashOpResponsePayload> type() {
    return HashOpResponsePayload.class;
  }

  @Override
  public HashOpResponsePayload createDefault() {
    return HashOpResponsePayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public HashOpResponsePayload createVariant() {
    return HashOpResponsePayload
        .builder()
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
