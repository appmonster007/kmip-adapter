package org.purplebean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PublicKeyLink;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PublicKeyLink Json Serialization Tests")
class PublicKeyLinkJsonTest extends AbstractJsonSerializationTestSuite<PublicKeyLink> {

  @Override
  public Class<PublicKeyLink> type() {
    return PublicKeyLink.class;
  }

  @Override
  public PublicKeyLink createDefault() {
    return PublicKeyLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public PublicKeyLink createVariant() {
    return PublicKeyLink.of(UniqueIdentifier.of("test-id"));
  }
}