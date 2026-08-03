package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ReplacedUniqueIdentifier Domain Tests")
class ReplacedUniqueIdentifierTest extends AbstractKmipDataTypeTestSuite<ReplacedUniqueIdentifier> {

  @Override
  protected Class<ReplacedUniqueIdentifier> type() {
    return ReplacedUniqueIdentifier.class;
  }

  @Override
  protected ReplacedUniqueIdentifier createDefault() {
    return ReplacedUniqueIdentifier
        .builder()
        .value("test-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}