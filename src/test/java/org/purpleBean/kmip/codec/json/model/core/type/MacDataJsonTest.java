package org.purpleBean.kmip.codec.json.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MacData JSON Serialization Tests")
class MacDataJsonTest extends AbstractJsonSerializationTestSuite<MacData> {

  @Override
  public Class<MacData> type() {
    return MacData.class;
  }

  @Override
  public MacData createDefault() {
    return MacData.of(ByteBuffer.wrap("test mac data".getBytes()));
  }

  @Override
  public MacData createVariant() {
    return MacData.of(ByteBuffer.wrap("variant mac data".getBytes()));
  }
}
