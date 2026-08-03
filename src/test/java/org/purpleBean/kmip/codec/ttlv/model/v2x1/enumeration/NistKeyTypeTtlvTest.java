package org.purplebean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.NistKeyType;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NistKeyType TTLV Serialization")
class NistKeyTypeTtlvTest extends AbstractTtlvSerializationTestSuite<NistKeyType> {
  @Override
  public Class<NistKeyType> type() {
    return NistKeyType.class;
  }

  @Override
  public NistKeyType createDefault() {
    return NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
  }

  @Override
  public NistKeyType createVariant() {
    return NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY.inst();
  }
}
