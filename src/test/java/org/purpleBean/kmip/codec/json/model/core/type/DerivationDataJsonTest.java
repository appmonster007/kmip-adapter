package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.DerivationData;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DerivationData JSON Serialization Tests")
class DerivationDataJsonTest extends AbstractJsonSerializationTestSuite<DerivationData> {

  @Override
  public Class<DerivationData> type() {
    return DerivationData.class;
  }

  @Override
  public DerivationData createDefault() {
    return DerivationData.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public DerivationData createVariant() {
    return DerivationData.of(new byte[] {0x04, 0x05, 0x06});
  }
}