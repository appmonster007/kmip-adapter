package org.purplebean.kmip.model.v2x1.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.RecommendedCurve;
import org.purplebean.kmip.model.core.type.D;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TransparentEcPrivateKey Domain Tests")
class TransparentEcPrivateKeyTest extends AbstractKmipStructureTestSuite<TransparentEcPrivateKey> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<TransparentEcPrivateKey> type() {
    return TransparentEcPrivateKey.class;
  }

  @Override
  protected TransparentEcPrivateKey createDefault() {
    KmipContext.setSpec(defaultSpec);
    var obj = TransparentEcPrivateKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
        .d(D.of(BigInteger.ONE))
        .build();
    KmipContext.clear();
    return obj;
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