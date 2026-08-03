package org.purplebean.kmip.codec.json.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.NonceValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("NonceValue Json Serialization Tests")
class NonceValueJsonTest extends AbstractJsonSerializationTestSuite<NonceValue> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<NonceValue> type() {
    return NonceValue.class;
  }

  @Override
  public NonceValue createDefault() {
    return NonceValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public NonceValue createVariant() {
    return NonceValue.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}