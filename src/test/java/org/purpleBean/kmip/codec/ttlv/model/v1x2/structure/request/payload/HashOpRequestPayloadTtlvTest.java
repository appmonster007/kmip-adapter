package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.request.payload.HashOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("HashOpRequestPayload Ttlv Serialization Tests")
class HashOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<HashOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<HashOpRequestPayload> type() {
    return HashOpRequestPayload.class;
  }

  @Override
  public HashOpRequestPayload createDefault() {
    return HashOpRequestPayload
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .build())
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public HashOpRequestPayload createVariant() {
    return HashOpRequestPayload
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .build())
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
