package org.purpleBean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ParentLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ParentLink Json Serialization Tests")
class ParentLinkJsonTest extends AbstractJsonSerializationTestSuite<ParentLink> {

  @Override
  public Class<ParentLink> type() {
    return ParentLink.class;
  }

  @Override
  public ParentLink createDefault() {
    return ParentLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public ParentLink createVariant() {
    return ParentLink.of(UniqueIdentifier.of("test-id"));
  }
}