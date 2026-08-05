package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;

/**
 * Benchmark subject for {@link IssuerAlternativeName}.
 */
public class IssuerAlternativeNameBenchmarkSubject
    extends KmipBenchmarkSubject<IssuerAlternativeName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link IssuerAlternativeNameBenchmarkSubject}.
   */
  public IssuerAlternativeNameBenchmarkSubject() throws Exception {
    IssuerAlternativeName issuerAlternativeName =
        IssuerAlternativeName.of("test-issuer-alt-name".getBytes());
    initialize(issuerAlternativeName, IssuerAlternativeName.class);
  }

  @Override
  public String name() {
    return "IssuerAlternativeName";
  }

}