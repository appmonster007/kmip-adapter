package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("RecommendedCurve JSON Serialization")
class RecommendedCurveJsonTest extends AbstractJsonSerializationSuite<RecommendedCurve> {
    @Override
    protected Class<RecommendedCurve> type() {
        return RecommendedCurve.class;
    }

    @Override
    protected RecommendedCurve createDefault() {
        return new RecommendedCurve(RecommendedCurve.Standard.P_192);
    }

    @Override
    protected RecommendedCurve createVariant() {
        return new RecommendedCurve(RecommendedCurve.Standard.K_163);
    }
}
