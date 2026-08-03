package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.PgpKey;
import org.purplebean.kmip.model.core.type.PgpKeyVersion;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PgpKey Xml Serialization Tests")
class PgpKeyXmlTest extends AbstractXmlSerializationTestSuite<PgpKey> {

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