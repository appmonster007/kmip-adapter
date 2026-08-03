package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SecretDataType TTLV Serialization")
class SecretDataTypeTtlvTest extends AbstractTtlvSerializationTestSuite<SecretDataType> {
  @Override
  public Class<SecretDataType> type() {
    return SecretDataType.class;
  }

  @Override
  public SecretDataType createDefault() {
    return SecretDataType.Standard.PASSWORD.inst();
  }

  @Override
  public SecretDataType createVariant() {
    return SecretDataType.Standard.SEED.inst();
  }
}
