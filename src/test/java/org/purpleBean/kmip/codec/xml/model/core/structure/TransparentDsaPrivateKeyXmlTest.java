package org.purplebean.kmip.codec.xml.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.TransparentDsaPrivateKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.X;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TransparentDsaPrivateKey XML Serialization Tests")
class TransparentDsaPrivateKeyXmlTest
    extends AbstractXmlSerializationTestSuite<TransparentDsaPrivateKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentDsaPrivateKey> type() {
    return TransparentDsaPrivateKey.class;
  }

  @Override
  public TransparentDsaPrivateKey createDefault() {
    return TransparentDsaPrivateKey.of(
        P.of(BigInteger.valueOf(1)),
        Q.of(BigInteger.valueOf(2)),
        G.of(BigInteger.valueOf(3)),
        X.of(BigInteger.valueOf(4))
    );
  }

  @Override
  public TransparentDsaPrivateKey createVariant() {
    return TransparentDsaPrivateKey.of(
        P.of(BigInteger.valueOf(5)),
        Q.of(BigInteger.valueOf(6)),
        G.of(BigInteger.valueOf(7)),
        X.of(BigInteger.valueOf(8))
    );
  }
}