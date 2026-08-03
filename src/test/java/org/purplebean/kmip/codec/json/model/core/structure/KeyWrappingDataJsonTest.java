package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.KeyWrappingData;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyWrappingData Json Serialization Tests")
class KeyWrappingDataJsonTest extends AbstractJsonSerializationTestSuite<KeyWrappingData> {

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