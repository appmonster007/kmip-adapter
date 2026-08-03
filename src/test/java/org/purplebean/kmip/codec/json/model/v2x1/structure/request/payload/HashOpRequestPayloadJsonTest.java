package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.v2x1.structure.request.payload.HashOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("HashOpRequestPayload Json Serialization Tests")
class HashOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<HashOpRequestPayload> {

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
            .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
  }

  @Override
  public HashOpRequestPayload createVariant() {
    return HashOpRequestPayload
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
  }
}