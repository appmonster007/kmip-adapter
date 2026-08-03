package org.purpleBean.kmip.model.v2x1.structure;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CommonAttributes Domain Tests")
class CommonAttributesTest extends AbstractKmipStructureTestSuite<CommonAttributes> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<CommonAttributes> type() {
    return CommonAttributes.class;
  }

  @Override
  public CommonAttributes createDefault() {
    return CommonAttributes.of(Collections.emptyList());
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // empty list is valid
  }
}
