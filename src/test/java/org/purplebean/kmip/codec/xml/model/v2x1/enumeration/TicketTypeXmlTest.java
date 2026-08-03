package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TicketType XML Serialization")
class TicketTypeXmlTest extends AbstractXmlSerializationTestSuite<TicketType> {
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
