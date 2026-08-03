package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ChildLink;

public class ChildLinkBenchmarkSubject extends KmipBenchmarkSubject<ChildLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public ChildLinkBenchmarkSubject() throws Exception {
    ChildLink subject = ChildLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, ChildLink.class);
  }

  @Override
  public String name() {
    return "ChildLink";
  }
}