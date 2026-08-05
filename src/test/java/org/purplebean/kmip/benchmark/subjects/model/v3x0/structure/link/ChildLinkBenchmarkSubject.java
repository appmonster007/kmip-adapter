package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ChildLink;

/**
 * Benchmark subject for {@link ChildLink}.
 */
public class ChildLinkBenchmarkSubject extends KmipBenchmarkSubject<ChildLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link ChildLinkBenchmarkSubject}.
   */
  public ChildLinkBenchmarkSubject() throws Exception {
    ChildLink subject = ChildLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, ChildLink.class);
  }

  @Override
  public String name() {
    return "ChildLink";
  }
}