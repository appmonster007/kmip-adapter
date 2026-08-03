package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.PrivateExponent;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("TransparentRsaPrivateKey Domain Tests")
class TransparentRsaPrivateKeyTest
    extends AbstractKmipStructureTestSuite<TransparentRsaPrivateKey> {

  @Override
  protected Class<TransparentRsaPrivateKey> type() {
    return TransparentRsaPrivateKey.class;
  }

  @Override
  protected TransparentRsaPrivateKey createDefault() {
    return TransparentRsaPrivateKey.of(
        Modulus.of(BigInteger.valueOf(1)),
        PrivateExponent.of(BigInteger.valueOf(2)),
        null,
        null,
        null,
        null,
        null,
        null
    );
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
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(Modulus.class);
    assertThat(values.get(1)).isInstanceOf(PrivateExponent.class);
  }
}