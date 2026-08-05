package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.LocatedItems;

/**
 * Benchmark subject for {@link LocatedItems}.
 */
public class LocatedItemsBenchmarkSubject extends KmipBenchmarkSubject<LocatedItems> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link LocatedItemsBenchmarkSubject}.
   */
  public LocatedItemsBenchmarkSubject() throws Exception {
    LocatedItems subject = LocatedItems.of(123);
    initialize(subject, LocatedItems.class);
  }

  @Override
  public String name() {
    return "LocatedItems";
  }
}