package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.MediaIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MediaIdentifier JSON Serialization Tests")
class MediaIdentifierJsonTest extends AbstractJsonSerializationTestSuite<MediaIdentifier> {

  @Override
  public Class<MediaIdentifier> type() {
    return MediaIdentifier.class;
  }

  @Override
  public MediaIdentifier createDefault() {
    return MediaIdentifier
        .builder()
        .value("test-media-id")
        .build();
  }

  @Override
  public MediaIdentifier createVariant() {
    return MediaIdentifier
        .builder()
        .value("another-media-id")
        .build();
  }
}