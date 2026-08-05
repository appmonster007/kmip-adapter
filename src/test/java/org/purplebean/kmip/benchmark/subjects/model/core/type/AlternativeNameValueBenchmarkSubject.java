package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;

/**
 * Benchmark subject for {@link AlternativeNameValue}.
 */
public class AlternativeNameValueBenchmarkSubject
    extends KmipBenchmarkSubject<AlternativeNameValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link AlternativeNameValueBenchmarkSubject}.
   */
  public AlternativeNameValueBenchmarkSubject() throws Exception {
    AlternativeNameValue alternativeNameValue = AlternativeNameValue.of("some value");
    initialize(alternativeNameValue, AlternativeNameValue.class);
  }

  @Override
  public String name() {
    return "AlternativeNameValue";
  }

}