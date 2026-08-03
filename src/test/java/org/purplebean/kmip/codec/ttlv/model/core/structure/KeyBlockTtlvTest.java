package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyBlock;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyBlock Ttlv Serialization Tests")
class KeyBlockTtlvTest extends AbstractTtlvSerializationTestSuite<KeyBlock> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<KeyBlock> type() {
    return KeyBlock.class;
  }

  @Override
  public KeyBlock createDefault() {
    return KeyBlock
        .builder()
        .keyFormatType(KeyFormatType.Standard.OPAQUE.inst())
        .build();
  }

  @Override
  public KeyBlock createVariant() {
    return KeyBlock
        .builder()
        .keyFormatType(KeyFormatType.Standard.PKCS_1.inst())
        .build();
  }
}