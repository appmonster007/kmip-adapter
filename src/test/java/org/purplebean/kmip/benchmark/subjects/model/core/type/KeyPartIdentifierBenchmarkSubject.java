package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.KeyPartIdentifier;

/**
 * Benchmark subject for {@link KeyPartIdentifier}.
 */
public class KeyPartIdentifierBenchmarkSubject extends KmipBenchmarkSubject<KeyPartIdentifier> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link KeyPartIdentifierBenchmarkSubject}.
   */
  public KeyPartIdentifierBenchmarkSubject() throws Exception {
    KeyPartIdentifier keyPartIdentifier = KeyPartIdentifier
        .builder()
        .value(1)
        .build();
    initialize(keyPartIdentifier, KeyPartIdentifier.class);
  }

  @Override
  public String name() {
    return "KeyPartIdentifier";
  }

}