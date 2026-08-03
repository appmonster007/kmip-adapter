package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.model.core.structure.KeyValueLocation;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyValueLocation Json Serialization Tests")
class KeyValueLocationJsonTest extends AbstractJsonSerializationTestSuite<KeyValueLocation> {

  @Override
  public Class<KeyValueLocation> type() {
    return KeyValueLocation.class;
  }

  @Override
  public KeyValueLocation createDefault() {
    return KeyValueLocation
        .builder()
        .keyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst())
        .keyValueLocationValue(KeyValueLocationValue
            .builder()
            .value("test")
            .build())
        .build();
  }
}