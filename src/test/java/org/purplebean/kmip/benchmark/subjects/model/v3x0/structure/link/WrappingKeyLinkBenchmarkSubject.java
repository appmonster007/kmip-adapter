package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.WrappingKeyLink;

/**
 * Benchmark subject for {@link WrappingKeyLink}.
 */
public class WrappingKeyLinkBenchmarkSubject extends KmipBenchmarkSubject<WrappingKeyLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  /**
   * Constructs a new {@link WrappingKeyLinkBenchmarkSubject}.
   */
  public WrappingKeyLinkBenchmarkSubject() throws Exception {
    WrappingKeyLink subject = WrappingKeyLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, WrappingKeyLink.class);
  }

  @Override
  public String name() {
    return "WrappingKeyLink";
  }
}