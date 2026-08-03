package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.type.TicketValue;

public class TicketBenchmarkSubject extends KmipBenchmarkSubject<Ticket> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public TicketBenchmarkSubject() throws Exception {
    Ticket subject = Ticket
        .builder()
        .ticketType(TicketType.Standard.LOGIN.inst())
        .ticketValue(TicketValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03})))
        .build();
    initialize(subject, Ticket.class);
  }

  @Override
  public String name() {
    return "Ticket";
  }
}