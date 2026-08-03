package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.AttestationCapability;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttestationCapability Ttlv Serialization Tests")
class AttestationCapabilityTtlvTest
    extends AbstractTtlvSerializationTestSuite<AttestationCapability> {

  @Override
  public Class<AttestationCapability> type() {
    return AttestationCapability.class;
  }

  @Override
  public AttestationCapability createDefault() {
    return AttestationCapability.of(true);
  }

  @Override
  public AttestationCapability createVariant() {
    return AttestationCapability.of(false);
  }
}