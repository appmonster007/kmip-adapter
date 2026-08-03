package org.purpleBean.kmip.codec.json.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.DecryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DecryptOpRequestPayload Json Serialization Tests")
class DecryptOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<DecryptOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DecryptOpRequestPayload> type() {
    return DecryptOpRequestPayload.class;
  }

  @Override
  public DecryptOpRequestPayload createDefault() {
    return DecryptOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public DecryptOpRequestPayload createVariant() {
    return DecryptOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
