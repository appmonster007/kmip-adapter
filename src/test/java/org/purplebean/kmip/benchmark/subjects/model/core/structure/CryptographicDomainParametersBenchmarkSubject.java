package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.CryptographicDomainParameters;
import org.purplebean.kmip.model.core.type.Qlength;

public class CryptographicDomainParametersBenchmarkSubject
    extends KmipBenchmarkSubject<CryptographicDomainParameters> {

  public CryptographicDomainParametersBenchmarkSubject() throws Exception {
    CryptographicDomainParameters cryptographicDomainParameters = CryptographicDomainParameters
        .builder()
        .qlength(Qlength.of(256))
        .recommendedCurve(RecommendedCurve.Standard.P_256.inst())
        .build();
    initialize(cryptographicDomainParameters, CryptographicDomainParameters.class);
  }

  @Override
  public String name() {
    return "CryptographicDomainParameters";
  }

}