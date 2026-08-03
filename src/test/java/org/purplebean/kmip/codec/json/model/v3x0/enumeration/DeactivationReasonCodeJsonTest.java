package org.purplebean.kmip.codec.json.model.v3x0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeactivationReasonCode JSON Serialization")
class DeactivationReasonCodeJsonTest
    extends AbstractJsonSerializationTestSuite<DeactivationReasonCode> {
  @Override
  public Class<DeactivationReasonCode> type() {
    return DeactivationReasonCode.class;
  }

  @Override
  public DeactivationReasonCode createDefault() {
    return DeactivationReasonCode.Standard.UNSPECIFIED.inst();
  }

  @Override
  public DeactivationReasonCode createVariant() {
    return DeactivationReasonCode.Standard.DEACTIVATION_DATE.inst();
  }
}
