package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PublicExponent;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TransparentRsaPublicKey TTLV Serialization Tests")
class TransparentRsaPublicKeyTtlvTest
    extends AbstractTtlvSerializationTestSuite<TransparentRsaPublicKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentRsaPublicKey> type() {
    return TransparentRsaPublicKey.class;
  }

  @Override
  public TransparentRsaPublicKey createDefault() {
    return TransparentRsaPublicKey.of(
        Modulus.of(BigInteger.valueOf(1)),
        PublicExponent.of(BigInteger.valueOf(2))
    );
  }

  @Override
  public TransparentRsaPublicKey createVariant() {
    return TransparentRsaPublicKey.of(
        Modulus.of(BigInteger.valueOf(3)),
        PublicExponent.of(BigInteger.valueOf(4))
    );
  }
}