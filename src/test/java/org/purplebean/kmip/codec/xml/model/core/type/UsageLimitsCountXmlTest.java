package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("UsageLimitsCount XML Serialization Tests")
class UsageLimitsCountXmlTest extends AbstractXmlSerializationTestSuite<UsageLimitsCount> {

  @Override
  public Class<UsageLimitsCount> type() {
    return UsageLimitsCount.class;
  }

  @Override
  public UsageLimitsCount createDefault() {
    return UsageLimitsCount
        .builder()
        .value(100L)
        .build();
  }

  @Override
  public UsageLimitsCount createVariant() {
    return UsageLimitsCount
        .builder()
        .value(200L)
        .build();
  }
}