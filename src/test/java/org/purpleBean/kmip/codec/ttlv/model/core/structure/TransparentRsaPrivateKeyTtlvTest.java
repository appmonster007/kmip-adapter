package org.purplebean.kmip.codec.ttlv.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.TransparentRsaPrivateKey;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.PrivateExponent;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TransparentRsaPrivateKey TTLV Serialization Tests")
class TransparentRsaPrivateKeyTtlvTest
    extends AbstractTtlvSerializationTestSuite<TransparentRsaPrivateKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentRsaPrivateKey> type() {
    return TransparentRsaPrivateKey.class;
  }

  @Override
  public TransparentRsaPrivateKey createDefault() {
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
  public TransparentRsaPrivateKey createVariant() {
    return TransparentRsaPrivateKey.of(
        Modulus.of(BigInteger.valueOf(3)),
        null,
        null,
        P.of(BigInteger.valueOf(4)),
        Q.of(BigInteger.valueOf(5)),
        null,
        null,
        null
    );
  }
}