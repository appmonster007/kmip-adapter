package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttributeValue;

/**
 * Benchmark subject for {@link AttributeValue}.
 */
public class AttributeValueBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AttributeValueBenchmarkSubject}.
   */
  public AttributeValueBenchmarkSubject() throws Exception {
    AttributeValue subject = AttributeValue.ofTextString("default-string");
    initialize(subject, AttributeValue.class);
  }

  @Override
  public String name() {
    return "AttributeValue";
  }
}