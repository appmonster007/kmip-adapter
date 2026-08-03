package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SymmetricKey Domain Tests")
class SymmetricKeyTest extends AbstractKmipStructureTestSuite<SymmetricKey> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<SymmetricKey> type() {
    return SymmetricKey.class;
  }

  @Override
  protected SymmetricKey createDefault() {
    return SymmetricKey
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