package org.purpleBean.kmip.benchmark.subjects.model.v3_0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.DerivationObjectLink;

public class DerivationObjectLinkBenchmarkSubject
    extends KmipBenchmarkSubject<DerivationObjectLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public DerivationObjectLinkBenchmarkSubject() throws Exception {
    DerivationObjectLink subject = DerivationObjectLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, DerivationObjectLink.class);
  }

  @Override
  public String name() {
    return "DerivationObjectLink";
  }
}