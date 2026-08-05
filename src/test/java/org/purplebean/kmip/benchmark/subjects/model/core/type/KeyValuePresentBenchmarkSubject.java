package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.KeyValuePresent;

/**
 * Benchmark subject for {@link KeyValuePresent}.
 */
public class KeyValuePresentBenchmarkSubject extends KmipBenchmarkSubject<KeyValuePresent> {

  /**
   * Constructs a new {@link KeyValuePresentBenchmarkSubject}.
   */
  public KeyValuePresentBenchmarkSubject() throws Exception {
    KeyValuePresent keyValuePresent = KeyValuePresent.of(Boolean.FALSE);
    initialize(keyValuePresent, KeyValuePresent.class);
  }

  @Override
  public String name() {
    return "KeyValuePresent";
  }

}