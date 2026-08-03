package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.DrbgAlgorithm;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DrbgAlgorithm XML Serialization")
class DrbgAlgorithmXmlTest extends AbstractXmlSerializationTestSuite<DrbgAlgorithm> {
  @Override
  public Class<DrbgAlgorithm> type() {
    return DrbgAlgorithm.class;
  }

  @Override
  public DrbgAlgorithm createDefault() {
    return DrbgAlgorithm.Standard.UNSPECIFIED.inst();
  }

  @Override
  public DrbgAlgorithm createVariant() {
    return DrbgAlgorithm.Standard.DUAL_EC.inst();
  }
}
