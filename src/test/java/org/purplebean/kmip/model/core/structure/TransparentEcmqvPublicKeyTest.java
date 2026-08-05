package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.QString;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TransparentEcmqvPublicKey Domain Tests")
class TransparentEcmqvPublicKeyTest
    extends AbstractKmipStructureTestSuite<TransparentEcmqvPublicKey> {

  @Override
  protected Class<TransparentEcmqvPublicKey> type() {
    return TransparentEcmqvPublicKey.class;
  }

  @Override
  protected TransparentEcmqvPublicKey createDefault() {
    return TransparentEcmqvPublicKey.of(
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