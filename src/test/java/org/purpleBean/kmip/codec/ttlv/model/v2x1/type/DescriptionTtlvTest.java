package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.Description;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Description Ttlv Serialization Tests")
class DescriptionTtlvTest extends AbstractTtlvSerializationTestSuite<Description> {

  @Override
  public Class<Description> type() {
    return Description.class;
  }

  @Override
  public Description createDefault() {
    return Description.of("default-string");
  }

  @Override
  public Description createVariant() {
    return Description.of("variant-string");
  }
}