package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.J;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.X;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TransparentDhPrivateKey Domain Tests")
class TransparentDhPrivateKeyTest extends AbstractKmipStructureTestSuite<TransparentDhPrivateKey> {

  @Override
  protected Class<TransparentDhPrivateKey> type() {
    return TransparentDhPrivateKey.class;
  }

  @Override
  protected TransparentDhPrivateKey createDefault() {
    return TransparentDhPrivateKey.of(
        P.of(BigInteger.valueOf(1)),
        Q.of(BigInteger.valueOf(2)),
        G.of(BigInteger.valueOf(3)),
        J.of(BigInteger.valueOf(4)),
        X.of(BigInteger.valueOf(5))
    );
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 3;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(5);
    assertThat(values.get(0)).isInstanceOf(P.class);
    assertThat(values.get(1)).isInstanceOf(Q.class);
    assertThat(values.get(2)).isInstanceOf(G.class);
    assertThat(values.get(3)).isInstanceOf(J.class);
    assertThat(values.get(4)).isInstanceOf(X.class);
  }
}