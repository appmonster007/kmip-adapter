package org.purplebean.kmip.codec.json.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SimpleRequestPayload Json Serialization Tests")
class SimpleRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SimpleRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<SimpleRequestPayload> type() {
    return SimpleRequestPayload.class;
  }

  @Override
  public SimpleRequestPayload createDefault() {
    return SimpleRequestPayload
        .builder()
        .build();
  }

  @Override
  public SimpleRequestPayload createVariant() {
    return SimpleRequestPayload
        .builder()
        .build();
  }
}