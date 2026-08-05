package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.nio.ByteBuffer;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.NonceValue;

/**
 * Benchmark subject for {@link NonceValue}.
 */
public class NonceValueBenchmarkSubject extends KmipBenchmarkSubject<NonceValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link NonceValueBenchmarkSubject}.
   */
  public NonceValueBenchmarkSubject() throws Exception {
    NonceValue subject = NonceValue.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
    initialize(subject, NonceValue.class);
  }

  @Override
  public String name() {
    return "NonceValue";
  }

}