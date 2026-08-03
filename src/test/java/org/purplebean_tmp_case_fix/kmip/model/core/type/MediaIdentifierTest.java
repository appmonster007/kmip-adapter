package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("MediaIdentifier Domain Tests")
class MediaIdentifierTest extends AbstractKmipDataTypeTestSuite<MediaIdentifier> {

  @Override
  protected Class<MediaIdentifier> type() {
    return MediaIdentifier.class;
  }

  @Override
  protected MediaIdentifier createDefault() {
    return MediaIdentifier
        .builder()
        .value("test-media-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}