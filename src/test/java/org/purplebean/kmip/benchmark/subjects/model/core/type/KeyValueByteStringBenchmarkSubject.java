package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.KeyValueByteString;

/**
 * Benchmark subject for {@link KeyValueByteString}.
 */
public class KeyValueByteStringBenchmarkSubject extends KmipBenchmarkSubject<KeyValueByteString> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link KeyValueByteStringBenchmarkSubject}.
   */
  public KeyValueByteStringBenchmarkSubject() throws Exception {
    KeyValueByteString keyValueByteString = KeyValueByteString.of(new byte[] {0x01, 0x02, 0x03});
    initialize(keyValueByteString, KeyValueByteString.class);
  }

  @Override
  public String name() {
    return "KeyValueByteString";
  }

}