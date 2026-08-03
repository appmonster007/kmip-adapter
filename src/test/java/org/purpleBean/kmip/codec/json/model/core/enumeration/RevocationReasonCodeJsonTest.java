package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RevocationReasonCode JSON Serialization")
class RevocationReasonCodeJsonTest
    extends AbstractJsonSerializationTestSuite<RevocationReasonCode> {
  @Override
  public Class<RevocationReasonCode> type() {
    return RevocationReasonCode.class;
  }

  @Override
  public RevocationReasonCode createDefault() {
    return RevocationReasonCode.Standard.UNSPECIFIED.inst();
  }

  @Override
  public RevocationReasonCode createVariant() {
    return RevocationReasonCode.Standard.KEY_COMPROMISE.inst();
  }
}
