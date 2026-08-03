package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LogoutOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("LogoutOpRequestPayload Ttlv Serialization Tests")
class LogoutOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<LogoutOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<LogoutOpRequestPayload> type() {
    return LogoutOpRequestPayload.class;
  }

  @Override
  public LogoutOpRequestPayload createDefault() {
    return LogoutOpRequestPayload
        .builder()
        .ticket(Ticket
            .builder()
            .ticketType(TicketType.Standard.LOGIN.inst())
            .ticketValue(TicketValue.of(new byte[] {0x01, 0x02, 0x03}))
            .build())
        .build();
  }

  @Override
  public LogoutOpRequestPayload createVariant() {
    return LogoutOpRequestPayload
        .builder()
        .ticket(Ticket
            .builder()
            .ticketType(TicketType.Standard.LOGIN.inst())
            .ticketValue(TicketValue.of(new byte[] {0x04, 0x05, 0x06, 0x07}))
            .build())
        .build();
  }
}