package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueBatchItemID;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UniqueBatchItemID JSON Serialization Tests")
class UniqueBatchItemIDJsonTest extends AbstractJsonSerializationTestSuite<UniqueBatchItemID> {

  @Override
  public Class<UniqueBatchItemID> type() {
    return UniqueBatchItemID.class;
  }

  @Override
  public UniqueBatchItemID createDefault() {
    return UniqueBatchItemID.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public UniqueBatchItemID createVariant() {
    return UniqueBatchItemID.of(new byte[] {0x04, 0x05, 0x06});
  }
}