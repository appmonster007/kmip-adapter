package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.IssuerDistinguishedName;

/**
 * Benchmark subject for {@link IssuerDistinguishedName}.
 */
public class IssuerDistinguishedNameBenchmarkSubject
    extends KmipBenchmarkSubject<IssuerDistinguishedName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link IssuerDistinguishedNameBenchmarkSubject}.
   */
  public IssuerDistinguishedNameBenchmarkSubject() throws Exception {
    IssuerDistinguishedName issuerDistinguishedName =
        IssuerDistinguishedName.of("test-issuer".getBytes());
    initialize(issuerDistinguishedName, IssuerDistinguishedName.class);
  }

  @Override
  public String name() {
    return "IssuerDistinguishedName";
  }

}
