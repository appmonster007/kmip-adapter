package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ParentLink;

public class ParentLinkBenchmarkSubject extends KmipBenchmarkSubject<ParentLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public ParentLinkBenchmarkSubject() throws Exception {
    ParentLink subject = ParentLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, ParentLink.class);
  }

  @Override
  public String name() {
    return "ParentLink";
  }
}