package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.NeverExtractable;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("NeverExtractable Json Serialization Tests")
class NeverExtractableJsonTest extends AbstractJsonSerializationTestSuite<NeverExtractable> {

  @Override
  public Class<NeverExtractable> type() {
    return NeverExtractable.class;
  }

  @Override
  public NeverExtractable createDefault() {
    return NeverExtractable.of(true);
  }

  @Override
  public NeverExtractable createVariant() {
    return NeverExtractable.of(false);
  }
}