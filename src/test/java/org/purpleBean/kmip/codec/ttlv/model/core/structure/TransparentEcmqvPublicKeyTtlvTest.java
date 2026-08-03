package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RecommendedCurve;
import org.purpleBean.kmip.model.core.structure.TransparentEcmqvPublicKey;
import org.purpleBean.kmip.model.core.type.QString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TransparentEcmqvPublicKey TTLV Serialization Tests")
class TransparentEcmqvPublicKeyTtlvTest
    extends AbstractTtlvSerializationTestSuite<TransparentEcmqvPublicKey> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TransparentEcmqvPublicKey> type() {
    return TransparentEcmqvPublicKey.class;
  }

  @Override
  public TransparentEcmqvPublicKey createDefault() {
    return TransparentEcmqvPublicKey.of(
        RecommendedCurve.Standard.P_192.inst(),
        QString.of("test".getBytes())
    );
  }

  @Override
  public TransparentEcmqvPublicKey createVariant() {
    return TransparentEcmqvPublicKey.of(
        RecommendedCurve.Standard.P_224.inst(),
        QString.of("test2".getBytes())
    );
  }
}