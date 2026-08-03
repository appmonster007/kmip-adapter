package org.purplebean.kmip.model.v2x1.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.nio.ByteBuffer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;
import org.purplebean.kmip.model.v2x1.type.TicketValue;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Ticket Domain Tests")
class TicketTest extends AbstractKmipStructureTestSuite<Ticket> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<Ticket> type() {
    return Ticket.class;
  }

  @Override
  public Ticket createDefault() {
    return Ticket
        .builder()
        .ticketType(TicketType.Standard.LOGIN.inst())
        .ticketValue(TicketValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03})))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
  }
}