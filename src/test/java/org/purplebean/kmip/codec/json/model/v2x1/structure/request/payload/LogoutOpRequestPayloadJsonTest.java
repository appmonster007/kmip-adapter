package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LogoutOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.TicketValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LogoutOpRequestPayload Json Serialization Tests")
class LogoutOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<LogoutOpRequestPayload> {

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