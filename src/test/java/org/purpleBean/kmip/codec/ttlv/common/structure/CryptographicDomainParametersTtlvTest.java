package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Qlength;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.CryptographicDomainParameters;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CryptographicDomainParameters Ttlv Serialization Tests")
class CryptographicDomainParametersTtlvTest extends AbstractTtlvSerializationSuite<CryptographicDomainParameters> {

    @Override
    protected Class<CryptographicDomainParameters> type() {
        return CryptographicDomainParameters.class;
    }

    @Override
    protected CryptographicDomainParameters createDefault() {
        return CryptographicDomainParameters.builder()
                .qlength(Qlength.of(256))
                .recommendedCurve(new RecommendedCurve(RecommendedCurve.Standard.P_256))
                .build();
    }
}