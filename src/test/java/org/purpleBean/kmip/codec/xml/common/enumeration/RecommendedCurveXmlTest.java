package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RecommendedCurve XML Serialization")
class RecommendedCurveXmlTest extends AbstractXmlSerializationTestSuite<RecommendedCurve> {
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
