package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SymmetricKey;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SymmetricKey Xml Serialization Tests")
class SymmetricKeyXmlTest extends AbstractXmlSerializationTestSuite<SymmetricKey> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<SymmetricKey> type() {
    return SymmetricKey.class;
  }

  @Override
  public SymmetricKey createDefault() {
    return SymmetricKey
        .builder()
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
  }

  @Override
  public SymmetricKey createVariant() {
    return SymmetricKey
        .builder()
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
            .build())
        .build();
  }
}