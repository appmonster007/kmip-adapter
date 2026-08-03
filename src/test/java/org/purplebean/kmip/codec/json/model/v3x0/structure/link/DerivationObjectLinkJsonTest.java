package org.purplebean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.DerivationObjectLink;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DerivationObjectLink Json Serialization Tests")
class DerivationObjectLinkJsonTest
    extends AbstractJsonSerializationTestSuite<DerivationObjectLink> {

  @Override
  public Class<DerivationObjectLink> type() {
    return DerivationObjectLink.class;
  }

  @Override
  public DerivationObjectLink createDefault() {
    return DerivationObjectLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public DerivationObjectLink createVariant() {
    return DerivationObjectLink.of(UniqueIdentifier.of("test-id"));
  }
}