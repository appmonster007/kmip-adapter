package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.MaximumItems;

/**
 * Benchmark subject for {@link MaximumItems}.
 */
public class MaximumItemsBenchmarkSubject extends KmipBenchmarkSubject<MaximumItems> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link MaximumItemsBenchmarkSubject}.
   */
  public MaximumItemsBenchmarkSubject() throws Exception {
    MaximumItems maximumItems = MaximumItems
        .builder()
        .value(100)
        .build();
    initialize(maximumItems, MaximumItems.class);
  }

  @Override
  public String name() {
    return "MaximumItems";
  }

}