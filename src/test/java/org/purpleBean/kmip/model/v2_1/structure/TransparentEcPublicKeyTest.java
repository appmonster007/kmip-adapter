package org.purpleBean.kmip.model.v2_1.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TransparentEcPublicKey Domain Tests")
class TransparentEcPublicKeyTest extends AbstractKmipStructureTestSuite<TransparentEcPublicKey> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<TransparentEcPublicKey> type() {
    return TransparentEcPublicKey.class;
  }

  @Override
  protected TransparentEcPublicKey createDefault() {
    KmipContext.setSpec(defaultSpec);
    var obj = TransparentEcPublicKey
        .builder()
        .recommendedCurve(RecommendedCurve.Standard.P_192.inst())
        .qString(QString.of(new byte[] {0x01, 0x02, 0x03}))
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
    assertThat(values.get(1)).isInstanceOf(QString.class);
  }
}