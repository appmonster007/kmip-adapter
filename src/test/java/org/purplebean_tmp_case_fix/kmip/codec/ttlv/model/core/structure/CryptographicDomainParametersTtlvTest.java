package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.structure.CryptographicDomainParameters;
import org.purplebean.kmip.model.core.type.Qlength;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CryptographicDomainParameters Ttlv Serialization Tests")
class CryptographicDomainParametersTtlvTest
    extends AbstractTtlvSerializationTestSuite<CryptographicDomainParameters> {

  @Override
  public Class<CryptographicDomainParameters> type() {
    return CryptographicDomainParameters.class;
  }

  @Override
  public CryptographicDomainParameters createDefault() {
    return CryptographicDomainParameters
        .builder()
        .qlength(Qlength.of(256))
        .recommendedCurve(RecommendedCurve.Standard.P_256.inst())
        .build();
  }
}