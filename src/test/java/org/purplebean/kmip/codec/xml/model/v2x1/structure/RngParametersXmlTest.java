package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.model.v2x1.structure.RngParameters;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngParameters Xml Serialization Tests")
class RngParametersXmlTest extends AbstractXmlSerializationTestSuite<RngParameters> {

  @Override
  public Class<RngParameters> type() {
    return RngParameters.class;
  }

  @Override
  public RngParameters createDefault() {
    return RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst());
  }

  @Override
  public RngParameters createVariant() {
    return RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst());
  }
}