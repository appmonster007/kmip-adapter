package org.purplebean.kmip.codec.json.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttestationAssertion;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttestationAssertion Json Serialization Tests")
class AttestationAssertionJsonTest
    extends AbstractJsonSerializationTestSuite<AttestationAssertion> {

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