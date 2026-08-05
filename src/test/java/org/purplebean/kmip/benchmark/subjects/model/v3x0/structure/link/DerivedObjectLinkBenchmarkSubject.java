package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.DerivedObjectLink;

/**
 * Benchmark subject for {@link DerivedObjectLink}.
 */
public class DerivedObjectLinkBenchmarkSubject extends KmipBenchmarkSubject<DerivedObjectLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link DerivedObjectLinkBenchmarkSubject}.
   */
  public DerivedObjectLinkBenchmarkSubject() throws Exception {
    DerivedObjectLink subject = DerivedObjectLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, DerivedObjectLink.class);
  }

  @Override
  public String name() {
    return "DerivedObjectLink";
  }
}