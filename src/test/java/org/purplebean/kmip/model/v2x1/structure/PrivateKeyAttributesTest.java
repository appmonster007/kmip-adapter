package org.purplebean.kmip.model.v2x1.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("PrivateKeyAttributes Domain Tests")
class PrivateKeyAttributesTest extends AbstractKmipStructureTestSuite<PrivateKeyAttributes> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<PrivateKeyAttributes> type() {
    return PrivateKeyAttributes.class;
  }

  @Override
  protected PrivateKeyAttributes createDefault() {
    return PrivateKeyAttributes.of(Collections.emptyList());
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
    assertThat(values).isEmpty();
  }
}
