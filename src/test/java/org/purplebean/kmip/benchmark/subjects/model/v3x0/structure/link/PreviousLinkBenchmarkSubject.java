package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.structure.link.PreviousLink;

public class PreviousLinkBenchmarkSubject extends KmipBenchmarkSubject<PreviousLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public PreviousLinkBenchmarkSubject() throws Exception {
    PreviousLink subject = PreviousLink.of("test-id");
    initialize(subject, PreviousLink.class);
  }

  @Override
  public String name() {
    return "PreviousLink";
  }
}