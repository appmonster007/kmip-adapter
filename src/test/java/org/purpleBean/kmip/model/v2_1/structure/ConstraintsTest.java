package org.purpleBean.kmip.model.v2_1.structure;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Constraints Domain Tests")
class ConstraintsTest extends AbstractKmipStructureTestSuite<Constraints> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<Constraints> type() {
    return Constraints.class;
  }

  @Override
  protected Constraints createDefault() {
    return Constraints.of(Collections.emptyList());
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
    // empty constraints has no components
  }
}
