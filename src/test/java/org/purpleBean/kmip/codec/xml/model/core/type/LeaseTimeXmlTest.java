package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LeaseTime XML Serialization Tests")
class LeaseTimeXmlTest extends AbstractXmlSerializationTestSuite<LeaseTime> {

  @Override
  public Class<LeaseTime> type() {
    return LeaseTime.class;
  }

  @Override
  public LeaseTime createDefault() {
    return LeaseTime
        .builder()
        .value(10)
        .build();
  }

  @Override
  public LeaseTime createVariant() {
    return LeaseTime
        .builder()
        .value(100)
        .build();
  }
}
