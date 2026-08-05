package org.purplebean.kmip.benchmark.subjects.model.core.type;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.AttributeIndex;

/**
 * Benchmark subject for {@link AttributeIndex}.
 */
public class AttributeIndexBenchmarkSubject extends KmipBenchmarkSubject<AttributeIndex> {

  /**
   * Constructs a new {@link AttributeIndexBenchmarkSubject}.
   */
  public AttributeIndexBenchmarkSubject() throws Exception {
    AttributeIndex attributeIndex = AttributeIndex
        .builder()
        .value(10)
        .build();
    initialize(attributeIndex, AttributeIndex.class);
  }

  @Override
  public String name() {
    return "AttributeIndex";
  }

}
