package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CryptographicLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;


@DisplayName("CryptographicLength TTLV Serialization Tests")
class CryptographicLengthTtlvTest extends AbstractTtlvSerializationTestSuite<CryptographicLength> {

  @Override
  public Class<CryptographicLength> type() {
    return CryptographicLength.class;
  }

  @Override
  public CryptographicLength createDefault() {
    return CryptographicLength.of(256);
  }

  @Override
  public CryptographicLength createVariant() {
    return CryptographicLength.of(512);
  }
}
