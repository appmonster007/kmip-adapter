package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Key;

/**
 * Benchmark subject for {@link Key}.
 */
public class KeyBenchmarkSubject extends KmipBenchmarkSubject<Key> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link KeyBenchmarkSubject}.
   */
  public KeyBenchmarkSubject() throws Exception {
    Key key = Key.of(new byte[] {0x01, 0x02, 0x03});
    initialize(key, Key.class);
  }

  @Override
  public String name() {
    return "Key";
  }

}