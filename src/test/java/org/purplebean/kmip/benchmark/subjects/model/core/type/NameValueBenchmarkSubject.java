package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.NameValue;

/**
 * Benchmark subject for {@link NameValue}.
 */
public class NameValueBenchmarkSubject extends KmipBenchmarkSubject<NameValue> {

  /**
   * Constructs a new {@link NameValueBenchmarkSubject}.
   */
  public NameValueBenchmarkSubject() throws Exception {
    NameValue nameValue = NameValue
        .builder()
        .value("some-name")
        .build();
    initialize(nameValue, NameValue.class);
  }

  @Override
  public String name() {
    return "NameValue";
  }

}
