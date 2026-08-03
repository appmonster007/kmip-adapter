package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.TicketValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TicketValue Ttlv Serialization Tests")
class TicketValueTtlvTest extends AbstractTtlvSerializationTestSuite<TicketValue> {

  @Override
  public Class<TicketValue> type() {
    return TicketValue.class;
  }

  @Override
  public TicketValue createDefault() {
    return TicketValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public TicketValue createVariant() {
    return TicketValue.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}