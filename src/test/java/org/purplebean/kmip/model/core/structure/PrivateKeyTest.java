package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("PrivateKey Domain Tests")
class PrivateKeyTest extends AbstractKmipStructureTestSuite<PrivateKey> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<PrivateKey> type() {
    return PrivateKey.class;
  }

  @Override
  protected PrivateKey createDefault() {
    return PrivateKey
        .builder()
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
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
    assertThat(values.getFirst()).isInstanceOf(KeyBlock.class);
  }
}