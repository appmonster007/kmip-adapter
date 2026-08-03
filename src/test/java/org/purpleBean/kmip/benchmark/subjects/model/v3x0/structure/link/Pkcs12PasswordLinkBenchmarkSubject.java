package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.link;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.Pkcs12PasswordLink;

public class Pkcs12PasswordLinkBenchmarkSubject extends KmipBenchmarkSubject<Pkcs12PasswordLink> {

  @Getter
  private final KmipSpec spec = KmipSpec.V3_0;

  public Pkcs12PasswordLinkBenchmarkSubject() throws Exception {
    Pkcs12PasswordLink subject = Pkcs12PasswordLink.of(UniqueIdentifier.of("test-id"));
    initialize(subject, Pkcs12PasswordLink.class);
  }

  @Override
  public String name() {
    return "Pkcs12PasswordLink";
  }
}