package org.purpleBean.kmip.codec.json.model.v2x1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2x1.enumeration.TicketType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TicketType JSON Serialization")
class TicketTypeJsonTest extends AbstractJsonSerializationTestSuite<TicketType> {
  @Override
  public Class<TicketType> type() {
    return TicketType.class;
  }

  @Override
  public TicketType createDefault() {
    return TicketType.Standard.LOGIN.inst();
  }

  @Override
  public TicketType createVariant() {
    return TicketType
        .register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion))
        .inst();
  }
}
