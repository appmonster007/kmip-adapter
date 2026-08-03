package org.purplebean.kmip.codec.json.model.core.structure;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.TransparentDsaPublicKey;
import org.purplebean.kmip.model.core.type.G;
import org.purplebean.kmip.model.core.type.P;
import org.purplebean.kmip.model.core.type.Q;
import org.purplebean.kmip.model.core.type.Y;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TransparentDsaPublicKey JSON Serialization Tests")
class TransparentDsaPublicKeyJsonTest
    extends AbstractJsonSerializationTestSuite<TransparentDsaPublicKey> {

  @Override
  public Class<TransparentDsaPublicKey> type() {
    return TransparentDsaPublicKey.class;
  }

  @Override
  public TransparentDsaPublicKey createDefault() {
    return TransparentDsaPublicKey.of(
        P.of(BigInteger.valueOf(1)),
        Q.of(BigInteger.valueOf(2)),
        G.of(BigInteger.valueOf(3)),
        Y.of(BigInteger.valueOf(4))
    );
  }

  @Override
  public TransparentDsaPublicKey createVariant() {
    return TransparentDsaPublicKey.of(
        P.of(BigInteger.valueOf(5)),
        Q.of(BigInteger.valueOf(6)),
        G.of(BigInteger.valueOf(7)),
        Y.of(BigInteger.valueOf(8))
    );
  }
}