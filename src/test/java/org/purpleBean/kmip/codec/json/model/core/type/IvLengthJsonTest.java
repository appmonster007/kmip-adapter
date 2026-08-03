package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.IvLength;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("IvLength JSON Serialization Tests")
class IvLengthJsonTest extends AbstractJsonSerializationTestSuite<IvLength> {

  @Override
  public Class<IvLength> type() {
    return IvLength.class;
  }

  @Override
  public IvLength createDefault() {
    return IvLength.of(128);
  }

  @Override
  public IvLength createVariant() {
    return IvLength.of(256);
  }
}