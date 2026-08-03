package org.purpleBean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.D;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TransparentEcdsaPrivateKey Domain Tests")
class TransparentEcdsaPrivateKeyTest
    extends AbstractKmipStructureTestSuite<TransparentEcdsaPrivateKey> {

  @Override
  protected Class<TransparentEcdsaPrivateKey> type() {
    return TransparentEcdsaPrivateKey.class;
  }

  @Override
  protected TransparentEcdsaPrivateKey createDefault() {
    return TransparentEcdsaPrivateKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        D.of(BigInteger.valueOf(1))
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
    assertThat(values.get(1)).isInstanceOf(D.class);
  }
}