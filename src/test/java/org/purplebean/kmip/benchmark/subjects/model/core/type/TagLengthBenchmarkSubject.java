package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.TagLength;

/**
 * Benchmark subject for {@link TagLength}.
 */
public class TagLengthBenchmarkSubject extends KmipBenchmarkSubject<TagLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TagLengthBenchmarkSubject}.
   */
  public TagLengthBenchmarkSubject() throws Exception {
    TagLength tagLength = TagLength.of(128);
    initialize(tagLength, TagLength.class);
  }

  @Override
  public String name() {
    return "TagLength";
  }

}