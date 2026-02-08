package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RecommendedCurve TTLV Serialization")
class RecommendedCurveTtlvTest extends AbstractTtlvSerializationTestSuite<RecommendedCurve> {
    @Override
    public Class<RecommendedCurve> type() {
        return RecommendedCurve.class;
    }

    @Override
    public RecommendedCurve createDefault() {
        return RecommendedCurve.Standard.P_192.inst();
    }

    @Override
    public RecommendedCurve createVariant() {
        return RecommendedCurve.Standard.K_163.inst();
    }
}
