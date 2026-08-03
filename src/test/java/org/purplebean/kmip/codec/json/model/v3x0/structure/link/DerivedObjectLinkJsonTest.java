package org.purplebean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.DerivedObjectLink;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DerivedObjectLink Json Serialization Tests")
class DerivedObjectLinkJsonTest extends AbstractJsonSerializationTestSuite<DerivedObjectLink> {

  @Override
  public Class<DerivedObjectLink> type() {
    return DerivedObjectLink.class;
  }

  @Override
  public DerivedObjectLink createDefault() {
    return DerivedObjectLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public DerivedObjectLink createVariant() {
    return DerivedObjectLink.of(UniqueIdentifier.of("test-id"));
  }
}