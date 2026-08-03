package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.IssuerDistinguishedName;

public class IssuerDistinguishedNameBenchmarkSubject
    extends KmipBenchmarkSubject<IssuerDistinguishedName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

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
