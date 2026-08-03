package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Issuer;

public class IssuerBenchmarkSubject extends KmipBenchmarkSubject<Issuer> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public IssuerBenchmarkSubject() throws Exception {
    Issuer issuer = Issuer
        .builder()
        .value("test-issuer")
        .build();
    initialize(issuer, Issuer.class);
  }

  @Override
  public String name() {
    return "Issuer";
  }

}