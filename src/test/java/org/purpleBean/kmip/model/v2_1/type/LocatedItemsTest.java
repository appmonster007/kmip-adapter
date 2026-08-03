package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("LocatedItems Domain Tests")
class LocatedItemsTest extends AbstractKmipDataTypeTestSuite<LocatedItems> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<LocatedItems> type() {
    return LocatedItems.class;
  }

  @Override
  protected LocatedItems createDefault() {
    return LocatedItems.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}