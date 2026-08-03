package org.purplebean.kmip.codec.xml.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.TransparentDhPublicKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.J;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.Y;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TransparentDhPublicKey XML Serialization Tests")
class TransparentDhPublicKeyXmlTest
    extends AbstractXmlSerializationTestSuite<TransparentDhPublicKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentDhPublicKey> type() {
    return TransparentDhPublicKey.class;
  }

  @Override
  public TransparentDhPublicKey createDefault() {
    return TransparentDhPublicKey.of(
        P.of(BigInteger.valueOf(1)),
        Q.of(BigInteger.valueOf(2)),
        G.of(BigInteger.valueOf(3)),
        J.of(BigInteger.valueOf(4)),
        Y.of(BigInteger.valueOf(5))
    );
  }

  @Override
  public TransparentDhPublicKey createVariant() {
    return TransparentDhPublicKey.of(
        P.of(BigInteger.valueOf(6)),
        Q.of(BigInteger.valueOf(7)),
        G.of(BigInteger.valueOf(8)),
        J.of(BigInteger.valueOf(9)),
        Y.of(BigInteger.valueOf(10))
    );
  }
}