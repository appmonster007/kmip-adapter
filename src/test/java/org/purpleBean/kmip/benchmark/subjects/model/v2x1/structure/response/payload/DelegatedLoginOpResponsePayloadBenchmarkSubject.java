package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2x1.structure.Ticket;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.DelegatedLoginOpResponsePayload;
import org.purpleBean.kmip.model.v2x1.type.TicketValue;

public class DelegatedLoginOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DelegatedLoginOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public DelegatedLoginOpResponsePayloadBenchmarkSubject() throws Exception {
    DelegatedLoginOpResponsePayload subject = DelegatedLoginOpResponsePayload
        .builder()
        .ticket(Ticket
            .builder()
            .ticketType(TicketType.Standard.LOGIN.inst())
            .ticketValue(TicketValue.of(new byte[] {0x01, 0x02, 0x03}))
            .build())
        .build();
    initialize(subject, DelegatedLoginOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "DelegatedLoginOpResponsePayload";
  }
}