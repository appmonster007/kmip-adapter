package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.CryptographicDomainParameters;
import org.purpleBean.kmip.model.core.type.Qlength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CryptographicDomainParameters Json Serialization Tests")
class CryptographicDomainParametersJsonTest extends AbstractJsonSerializationTestSuite<CryptographicDomainParameters> {

    @Override
    public Class<CryptographicDomainParameters> type() {
        return CryptographicDomainParameters.class;
    }

    @Override
    public CryptographicDomainParameters createDefault() {
        return CryptographicDomainParameters.builder()
                .qlength(Qlength.of(256))
                .recommendedCurve(RecommendedCurve.Standard.P_256.inst())
                .build();
    }
}