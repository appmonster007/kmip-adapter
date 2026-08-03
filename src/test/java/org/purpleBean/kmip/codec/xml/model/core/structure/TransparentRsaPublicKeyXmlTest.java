package org.purplebean.kmip.codec.xml.model.core.structure;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.TransparentRsaPublicKey;
import org.purplebean.kmip.model.core.type.Modulus;
import org.purplebean.kmip.model.core.type.PublicExponent;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TransparentRsaPublicKey XML Serialization Tests")
class TransparentRsaPublicKeyXmlTest
    extends AbstractXmlSerializationTestSuite<TransparentRsaPublicKey> {

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