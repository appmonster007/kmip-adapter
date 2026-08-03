package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacData TTLV Serialization Tests")
class MacDataTtlvTest extends AbstractTtlvSerializationTestSuite<MacData> {

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
