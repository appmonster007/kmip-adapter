package org.purpleBean.kmip.codec.json.model.v3_0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacedObjectLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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