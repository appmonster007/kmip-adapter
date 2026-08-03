package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ReplacementObjectLink;

public class ReplacementObjectLinkBenchmarkSubject
    extends KmipBenchmarkSubject<ReplacementObjectLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public ReplacementObjectLinkBenchmarkSubject() throws Exception {
    ReplacementObjectLink subject = ReplacementObjectLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, ReplacementObjectLink.class);
  }

  @Override
  public String name() {
    return "ReplacementObjectLink";
  }
}