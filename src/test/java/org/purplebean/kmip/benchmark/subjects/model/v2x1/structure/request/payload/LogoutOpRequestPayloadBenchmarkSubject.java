package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.TicketType;
import org.purplebean.kmip.model.v2x1.structure.Ticket;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LogoutOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.TicketValue;

/**
 * Benchmark subject for {@link LogoutOpRequestPayload}.
 */
public class LogoutOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LogoutOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link LogoutOpRequestPayloadBenchmarkSubject}.
   */
  public LogoutOpRequestPayloadBenchmarkSubject() throws Exception {
    LogoutOpRequestPayload subject = LogoutOpRequestPayload
        .builder()
        .ticket(Ticket
            .builder()
            .ticketType(TicketType.Standard.LOGIN.inst())
            .ticketValue(TicketValue.of(new byte[] {0x01, 0x02, 0x03}))
            .build())
        .build();
    initialize(subject, LogoutOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "LogoutOpRequestPayload";
  }
}