package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RecommendedCurve XML Serialization")
class RecommendedCurveXmlTest extends AbstractXmlSerializationTestSuite<RecommendedCurve> {
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
