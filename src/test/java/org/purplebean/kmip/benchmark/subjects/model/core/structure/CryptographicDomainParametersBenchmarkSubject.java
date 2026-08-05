package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.CryptographicDomainParameters;
import org.purplebean.kmip.model.core.type.Qlength;

/**
 * Benchmark subject for {@link CryptographicDomainParameters}.
 */
public class CryptographicDomainParametersBenchmarkSubject
    extends KmipBenchmarkSubject<CryptographicDomainParameters> {

  /**
   * Constructs a new {@link CryptographicDomainParametersBenchmarkSubject}.
   */
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