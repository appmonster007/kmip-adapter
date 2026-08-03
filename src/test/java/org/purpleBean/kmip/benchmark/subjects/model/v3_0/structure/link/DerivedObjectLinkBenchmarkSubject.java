package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.DerivedObjectLink;

public class DerivedObjectLinkBenchmarkSubject extends KmipBenchmarkSubject<DerivedObjectLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public DerivedObjectLinkBenchmarkSubject() throws Exception {
    DerivedObjectLink subject = DerivedObjectLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, DerivedObjectLink.class);
  }

  @Override
  public String name() {
    return "DerivedObjectLink";
  }
}