package org.purpleBean.kmip.model.v2_1.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("AttributeReference Domain Tests")
class AttributeReferenceTest extends AbstractKmipStructureTestSuite<AttributeReference> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AttributeReference> type() {
    return AttributeReference.class;
  }

  @Override
  protected AttributeReference createDefault() {
    return AttributeReference
        .builder()
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}