package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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
