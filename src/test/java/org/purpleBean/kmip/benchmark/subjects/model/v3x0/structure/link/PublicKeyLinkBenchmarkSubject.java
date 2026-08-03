package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.PublicKeyLink;

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