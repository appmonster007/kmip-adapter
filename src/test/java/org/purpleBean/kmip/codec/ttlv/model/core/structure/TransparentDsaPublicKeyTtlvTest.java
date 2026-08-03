package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.TransparentDsaPublicKey;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TransparentDsaPublicKey Ttlv Serialization Tests")
class TransparentDsaPublicKeyTtlvTest
    extends AbstractTtlvSerializationTestSuite<TransparentDsaPublicKey> {

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