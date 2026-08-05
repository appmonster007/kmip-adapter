package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.structure.link.NextLink;

/**
 * Benchmark subject for {@link NextLink}.
 */
public class NextLinkBenchmarkSubject extends KmipBenchmarkSubject<NextLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link NextLinkBenchmarkSubject}.
   */
  public NextLinkBenchmarkSubject() throws Exception {
    NextLink subject = NextLink.of("test-id");
    initialize(subject, NextLink.class);
  }

  @Override
  public String name() {
    return "NextLink";
  }
}