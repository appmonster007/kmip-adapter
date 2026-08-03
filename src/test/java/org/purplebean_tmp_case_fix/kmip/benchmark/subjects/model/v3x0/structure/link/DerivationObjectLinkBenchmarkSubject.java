package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.DerivationObjectLink;

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