package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("PgpKey Domain Tests")
class PgpKeyTest extends AbstractKmipStructureTestSuite<PgpKey> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<PgpKey> type() {
    return PgpKey.class;
  }

  @Override
  protected PgpKey createDefault() {
    return PgpKey
        .builder()
        .pgpKeyVersion(PgpKeyVersion.of(4))
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
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(PgpKeyVersion.class);
    assertThat(values.get(1)).isInstanceOf(KeyBlock.class);
  }
}