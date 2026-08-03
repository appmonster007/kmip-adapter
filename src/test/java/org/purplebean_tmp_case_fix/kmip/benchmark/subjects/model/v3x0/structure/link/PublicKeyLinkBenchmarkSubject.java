package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PublicKeyLink;

public class PublicKeyLinkBenchmarkSubject extends KmipBenchmarkSubject<PublicKeyLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public PublicKeyLinkBenchmarkSubject() throws Exception {
    PublicKeyLink subject = PublicKeyLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, PublicKeyLink.class);
  }

  @Override
  public String name() {
    return "PublicKeyLink";
  }
}