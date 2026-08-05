package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.QString;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TransparentEcdhPublicKey Domain Tests")
class TransparentEcdhPublicKeyTest
    extends AbstractKmipStructureTestSuite<TransparentEcdhPublicKey> {

  @Override
  protected Class<TransparentEcdhPublicKey> type() {
    return TransparentEcdhPublicKey.class;
  }

  @Override
  protected TransparentEcdhPublicKey createDefault() {
    return TransparentEcdhPublicKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        QString.of("test".getBytes())
    );
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
    assertThat(values.get(0)).isInstanceOf(RecommendedCurve.class);
    assertThat(values.get(1)).isInstanceOf(QString.class);
  }
}