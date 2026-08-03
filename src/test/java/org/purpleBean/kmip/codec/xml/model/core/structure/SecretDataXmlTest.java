package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.KeyBlock;
import org.purpleBean.kmip.model.core.structure.SecretData;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SecretData Xml Serialization Tests")
class SecretDataXmlTest extends AbstractXmlSerializationTestSuite<SecretData> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<SecretData> type() {
    return SecretData.class;
  }

  @Override
  public SecretData createDefault() {
    return SecretData
        .builder()
        .secretDataType(SecretDataType.Standard.PASSWORD.inst())
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
            .build())
        .build();
  }

  @Override
  public SecretData createVariant() {
    return SecretData
        .builder()
        .secretDataType(SecretDataType.Standard.SEED.inst())
        .keyBlock(KeyBlock
            .builder()
            .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
            .build())
        .build();
  }
}