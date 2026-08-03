package org.purpleBean.kmip.codec.json.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.MacVerifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MacVerifyOpRequestPayload Json Serialization Tests")
class MacVerifyOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<MacVerifyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<MacVerifyOpRequestPayload> type() {
    return MacVerifyOpRequestPayload.class;
  }

  @Override
  public MacVerifyOpRequestPayload createDefault() {
    return MacVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .macData(MacData.of(new byte[] {4, 5, 6}))
        .build();
  }

  @Override
  public MacVerifyOpRequestPayload createVariant() {
    return MacVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .data(DataByteString.of(new byte[] {7, 8, 9}))
        .macData(MacData.of(new byte[] {10, 11, 12}))
        .build();
  }
}
