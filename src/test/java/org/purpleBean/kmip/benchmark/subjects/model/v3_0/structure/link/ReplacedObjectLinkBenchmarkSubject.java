package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacedObjectLink;

public class ReplacedObjectLinkBenchmarkSubject extends KmipBenchmarkSubject<ReplacedObjectLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public ReplacedObjectLinkBenchmarkSubject() throws Exception {
    ReplacedObjectLink subject = ReplacedObjectLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, ReplacedObjectLink.class);
  }

  @Override
  public String name() {
    return "ReplacedObjectLink";
  }
}