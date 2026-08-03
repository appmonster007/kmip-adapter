package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.response.payload.DelegatedLoginOpResponsePayload;
import org.purplebean.kmip.model.v2x1.type.TicketValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DelegatedLoginOpResponsePayload Ttlv Serialization Tests")
class DelegatedLoginOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DelegatedLoginOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<DelegatedLoginOpResponsePayload> type() {
    return DelegatedLoginOpResponsePayload.class;
  }

  @Override
  public DelegatedLoginOpResponsePayload createDefault() {
    return DelegatedLoginOpResponsePayload
        .builder()
        .ticket(Ticket
            .builder()
            .ticketType(TicketType.Standard.LOGIN.inst())
            .ticketValue(TicketValue.of(new byte[] {0x01, 0x02, 0x03}))
            .build())
        .build();
  }

  @Override
  public DelegatedLoginOpResponsePayload createVariant() {
    return DelegatedLoginOpResponsePayload
        .builder()
        .ticket(Ticket
            .builder()
            .ticketType(TicketType.Standard.LOGIN.inst())
            .ticketValue(TicketValue.of(new byte[] {0x04, 0x05, 0x06, 0x07}))
            .build())
        .build();
  }
}