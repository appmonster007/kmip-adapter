package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.MACSignature;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MACSignature JSON Serialization Tests")
class MACSignatureJsonTest extends AbstractJsonSerializationTestSuite<MACSignature> {

  @Override
  public Class<MACSignature> type() {
    return MACSignature.class;
  }

  @Override
  public MACSignature createDefault() {
    return MACSignature.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public MACSignature createVariant() {
    return MACSignature.of(new byte[] {0x04, 0x05, 0x06});
  }
}