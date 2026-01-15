package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RecommendedCurve JSON Serialization")
class RecommendedCurveJsonTest extends AbstractJsonSerializationTestSuite<RecommendedCurve> {
    @Override
    protected Class<RecommendedCurve> type() {
        return RecommendedCurve.class;
    }

    @Override
    protected RecommendedCurve createDefault() {
        return RecommendedCurve.Standard.P_192.inst();
    }

    @Override
    protected RecommendedCurve createVariant() {
        return RecommendedCurve.Standard.K_163.inst();
    }
}
