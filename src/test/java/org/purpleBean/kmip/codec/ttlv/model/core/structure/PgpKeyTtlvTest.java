package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.PgpKey;
import org.purpleBean.kmip.model.core.type.PgpKeyVersion;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PgpKey Ttlv Serialization Tests")
class PgpKeyTtlvTest extends AbstractTtlvSerializationTestSuite<PgpKey> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<PgpKey> type() {
    return PgpKey.class;
  }

  @Override
  public PgpKey createDefault() {
    return PgpKey
        .builder()
        .pgpKeyVersion(PgpKeyVersion.of(4))
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
  }

  @Override
  public PgpKey createVariant() {
    return PgpKey
        .builder()
        .pgpKeyVersion(PgpKeyVersion.of(5))
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
            .build())
        .build();
  }
}