package org.purpleBean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.enumeration.TicketType;

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
