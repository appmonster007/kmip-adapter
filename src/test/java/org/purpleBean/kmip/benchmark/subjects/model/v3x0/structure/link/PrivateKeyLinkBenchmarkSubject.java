package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PrivateKeyLink;

public class PrivateKeyLinkBenchmarkSubject extends KmipBenchmarkSubject<PrivateKeyLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public PrivateKeyLinkBenchmarkSubject() throws Exception {
    PrivateKeyLink subject = PrivateKeyLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, PrivateKeyLink.class);
  }

  @Override
  public String name() {
    return "PrivateKeyLink";
  }
}