package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("MessageExtension Domain Tests")
class MessageExtensionTest extends AbstractKmipStructureTestSuite<MessageExtension> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<MessageExtension> type() {
    return MessageExtension.class;
  }

  @Override
  protected MessageExtension createDefault() {
    return MessageExtension
        .builder()
        .vendorIdentification(VendorIdentification.of("test-vendor"))
        .build();
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
    assertThat(values).hasSize(1);
    assertThat(values.getFirst()).isInstanceOf(VendorIdentification.class);
  }
}