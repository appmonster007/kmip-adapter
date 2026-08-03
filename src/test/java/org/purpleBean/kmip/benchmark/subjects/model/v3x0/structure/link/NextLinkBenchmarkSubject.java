package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.NextLink;

public class NextLinkBenchmarkSubject extends KmipBenchmarkSubject<NextLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public NextLinkBenchmarkSubject() throws Exception {
    NextLink subject = NextLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, NextLink.class);
  }

  @Override
  public String name() {
    return "NextLink";
  }
}