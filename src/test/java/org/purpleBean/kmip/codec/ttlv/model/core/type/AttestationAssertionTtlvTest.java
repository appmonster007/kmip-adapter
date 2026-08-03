package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttestationAssertion Ttlv Serialization Tests")
class AttestationAssertionTtlvTest
    extends AbstractTtlvSerializationTestSuite<AttestationAssertion> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<AttestationAssertion> type() {
    return AttestationAssertion.class;
  }

  @Override
  public AttestationAssertion createDefault() {
    return AttestationAssertion.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public AttestationAssertion createVariant() {
    return AttestationAssertion.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}