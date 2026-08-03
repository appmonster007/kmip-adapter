package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.ExtensionName;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ExtensionInformation Domain Tests")
class ExtensionInformationTest extends AbstractKmipStructureTestSuite<ExtensionInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ExtensionInformation> type() {
    return ExtensionInformation.class;
  }

  @Override
  protected ExtensionInformation createDefault() {
    return ExtensionInformation
        .builder()
        .extensionName(ExtensionName.of("test-extension"))
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
    assertThat(values.getFirst()).isInstanceOf(ExtensionName.class);
  }
}