package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.enumeration.SecretDataType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.model.core.structure.SecretData;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SecretData Ttlv Serialization Tests")
class SecretDataTtlvTest extends AbstractTtlvSerializationTestSuite<SecretData> {

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