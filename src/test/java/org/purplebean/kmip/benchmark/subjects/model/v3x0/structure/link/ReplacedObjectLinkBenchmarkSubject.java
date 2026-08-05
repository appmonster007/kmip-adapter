package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ReplacedObjectLink;

/**
 * Benchmark subject for {@link ReplacedObjectLink}.
 */
public class ReplacedObjectLinkBenchmarkSubject extends KmipBenchmarkSubject<ReplacedObjectLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link ReplacedObjectLinkBenchmarkSubject}.
   */
  public ReplacedObjectLinkBenchmarkSubject() throws Exception {
    ReplacedObjectLink subject = ReplacedObjectLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, ReplacedObjectLink.class);
  }

  @Override
  public String name() {
    return "ReplacedObjectLink";
  }
}