package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ReplaceExisting;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ReplaceExisting Json Serialization Tests")
class ReplaceExistingJsonTest extends AbstractJsonSerializationTestSuite<ReplaceExisting> {

  @Override
  public Class<ReplaceExisting> type() {
    return ReplaceExisting.class;
  }

  @Override
  public ReplaceExisting createDefault() {
    return ReplaceExisting.of(true);
  }

  @Override
  public ReplaceExisting createVariant() {
    return ReplaceExisting.of(false);
  }
}