package org.purplebean.kmip.model.v2x1.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Attribute Domain Tests")
class AttributeTest extends AbstractKmipStructureTestSuite<Attribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<Attribute> type() {
    return Attribute.class;
  }

  @Override
  protected Attribute createDefault() {
    return Attribute
        .builder()
        .vendorIdentification(VendorIdentification.of("vendor"))
        .attributeName(AttributeName.of("TestAttr"))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSizeGreaterThanOrEqualTo(2);
  }
}