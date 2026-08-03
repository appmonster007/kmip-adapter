package org.purpleBean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.PublicKeyLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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