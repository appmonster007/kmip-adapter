package org.purplebean.kmip.codec.json.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.DigestedData;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DigestedData Json Serialization Tests")
class DigestedDataJsonTest extends AbstractJsonSerializationTestSuite<DigestedData> {

  @Override
  public Class<DigestedData> type() {
    return DigestedData.class;
  }

  @Override
  public DigestedData createDefault() {
    return DigestedData.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public DigestedData createVariant() {
    return DigestedData.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}