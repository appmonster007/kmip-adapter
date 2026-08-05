package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttributeName;

/**
 * Benchmark subject for {@link AttributeName}.
 */
public class AttributeNameBenchmarkSubject extends KmipBenchmarkSubject<AttributeName> {

  /**
   * Constructs a new {@link AttributeNameBenchmarkSubject}.
   */
  public AttributeNameBenchmarkSubject() throws Exception {
    AttributeName attributeName = AttributeName
        .builder()
        .value("attribute name")
        .build();
    initialize(attributeName, AttributeName.class);
  }

  @Override
  public String name() {
    return "AttributeName";
  }

}
