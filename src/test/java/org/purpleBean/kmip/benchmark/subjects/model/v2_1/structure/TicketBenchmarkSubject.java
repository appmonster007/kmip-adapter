package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;

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