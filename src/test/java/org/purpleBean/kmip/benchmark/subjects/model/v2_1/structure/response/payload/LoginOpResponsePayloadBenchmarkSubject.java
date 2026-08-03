package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.LoginOpResponsePayload;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;

public class LoginOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LoginOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public LoginOpResponsePayloadBenchmarkSubject() throws Exception {
    LoginOpResponsePayload subject = LoginOpResponsePayload
        .builder()
        .ticket(Ticket
            .builder()
            .ticketType(TicketType.Standard.LOGIN.inst())
            .ticketValue(TicketValue.of(new byte[] {0x01, 0x02, 0x03}))
            .build())
        .build();
    initialize(subject, LoginOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "LoginOpResponsePayload";
  }
}