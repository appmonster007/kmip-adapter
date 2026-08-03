package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.model.core.TtlvDataType;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("VendorExtension Domain Tests")
class VendorExtensionTest extends AbstractKmipStructureTestSuite<VendorExtension> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<VendorExtension> type() {
    return VendorExtension.class;
  }

  @Override
  protected VendorExtension createDefault() {
    return VendorExtension
        .builder()
        .ttlvDataType(TtlvDataType
            .builder()
            .kmipTag(KmipTag.Standard.UNIQUE_IDENTIFIER.inst())
            .encodingType(EncodingType.TEXT_STRING)
            .value("test-value")
            .build())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(TtlvDataType.class);
  }
}
