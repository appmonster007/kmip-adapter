package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.HashOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("HashOpRequestPayload Xml Serialization Tests")
class HashOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<HashOpRequestPayload> {

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