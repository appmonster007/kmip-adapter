package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Issuer;

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