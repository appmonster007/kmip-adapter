package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.EncryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("EncryptOpRequestPayload Json Serialization Tests")
class EncryptOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<EncryptOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<EncryptOpRequestPayload> type() {
    return EncryptOpRequestPayload.class;
  }

  @Override
  public EncryptOpRequestPayload createDefault() {
    return EncryptOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public EncryptOpRequestPayload createVariant() {
    return EncryptOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
