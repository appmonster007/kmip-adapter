package org.purplebean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.model.v2x1.structure.RngParameters;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RngParameters Json Serialization Tests")
class RngParametersJsonTest extends AbstractJsonSerializationTestSuite<RngParameters> {

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