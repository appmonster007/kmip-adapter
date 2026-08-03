package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.CapabilityInformation;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CapabilityInformation Ttlv Serialization Tests")
class CapabilityInformationTtlvTest
    extends AbstractTtlvSerializationTestSuite<CapabilityInformation> {

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