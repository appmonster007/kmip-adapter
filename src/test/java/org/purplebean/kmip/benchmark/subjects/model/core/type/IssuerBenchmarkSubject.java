package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Issuer;

/**
 * Benchmark subject for {@link Issuer}.
 */
public class IssuerBenchmarkSubject extends KmipBenchmarkSubject<Issuer> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link IssuerBenchmarkSubject}.
   */
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