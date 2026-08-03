package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.KeyWrappingData;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyWrappingData Ttlv Serialization Tests")
class KeyWrappingDataTtlvTest extends AbstractTtlvSerializationTestSuite<KeyWrappingData> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<KeyWrappingData> type() {
    return KeyWrappingData.class;
  }

  @Override
  public KeyWrappingData createDefault() {
    return KeyWrappingData
        .builder()
        .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
        .build();
  }

  @Override
  public KeyWrappingData createVariant() {
    return KeyWrappingData
        .builder()
        .wrappingMethod(WrappingMethod.Standard.MAC_SIGN.inst())
        .build();
  }
}