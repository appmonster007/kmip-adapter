package org.purpleBean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.CapabilityInformation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CapabilityInformation Json Serialization Tests")
class CapabilityInformationJsonTest
    extends AbstractJsonSerializationTestSuite<CapabilityInformation> {

  @Override
  public Class<CapabilityInformation> type() {
    return CapabilityInformation.class;
  }

  @Override
  public CapabilityInformation createDefault() {
    return CapabilityInformation
        .builder()
        .build();
  }

  @Override
  public CapabilityInformation createVariant() {
    return CapabilityInformation
        .builder()
        .build();
  }
}