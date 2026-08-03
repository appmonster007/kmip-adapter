package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;

public class TicketTypeBenchmarkSubject extends KmipBenchmarkSubject<TicketType> {

  public TicketTypeBenchmarkSubject() throws Exception {
    TicketType ticketType = TicketType.Standard.LOGIN.inst();
    initialize(ticketType, TicketType.class);
  }

  @Override
  public String name() {
    return "TicketType";
  }

}
