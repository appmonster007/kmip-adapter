package org.purplebean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ReplacedObjectLink;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ReplacedObjectLink Json Serialization Tests")
class ReplacedObjectLinkJsonTest extends AbstractJsonSerializationTestSuite<ReplacedObjectLink> {

  @Override
  public Class<ReplacedObjectLink> type() {
    return ReplacedObjectLink.class;
  }

  @Override
  public ReplacedObjectLink createDefault() {
    return ReplacedObjectLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public ReplacedObjectLink createVariant() {
    return ReplacedObjectLink.of(UniqueIdentifier.of("test-id"));
  }
}