package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngAlgorithm XML Serialization")
class RngAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<RngAlgorithm> {
  @Override
  public Class<RngAlgorithm> type() {
    return RngAlgorithm.class;
  }

  @Override
  public RngAlgorithm createDefault() {
    return RngAlgorithm.Standard.UNSPECIFIED.inst();
  }

  @Override
  public RngAlgorithm createVariant() {
    return RngAlgorithm.Standard.FIPS_186_2.inst();
  }
}
