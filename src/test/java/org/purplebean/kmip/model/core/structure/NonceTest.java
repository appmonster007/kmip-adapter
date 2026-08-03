package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.NonceId;
import org.purplebean.kmip.model.core.type.NonceValue;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("Nonce Domain Tests")
class NonceTest extends AbstractKmipStructureTestSuite<Nonce> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<Nonce> type() {
    return Nonce.class;
  }

  @Override
  protected Nonce createDefault() {
    return Nonce
        .builder()
        .nonceId(NonceId.of("test-id".getBytes()))
        .nonceValue(NonceValue.of(new byte[8]))
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
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(NonceId.class);
    assertThat(values.get(1)).isInstanceOf(NonceValue.class);
  }
}