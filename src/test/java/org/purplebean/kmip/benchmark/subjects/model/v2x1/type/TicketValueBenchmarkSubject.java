package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.TicketValue;

/**
 * Benchmark subject for {@link TicketValue}.
 */
public class TicketValueBenchmarkSubject extends KmipBenchmarkSubject<TicketValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  /**
   * Constructs a new {@link TicketValueBenchmarkSubject}.
   */
  public TicketValueBenchmarkSubject() throws Exception {
    TicketValue subject = TicketValue.of(
        ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));  // TODO: Create a default instance
    initialize(subject, TicketValue.class);
  }

  @Override
  public String name() {
    return "TicketValue";
  }
}