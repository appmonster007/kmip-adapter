package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.link.PublicKeyLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PublicKeyLink Xml Serialization Tests")
class PublicKeyLinkXmlTest extends AbstractXmlSerializationTestSuite<PublicKeyLink> {

  @Override
  public Class<PublicKeyLink> type() {
    return PublicKeyLink.class;
  }

  @Override
  public PublicKeyLink createDefault() {
    return PublicKeyLink.of("test-id");
  }

  @Override
  public PublicKeyLink createVariant() {
    return PublicKeyLink.of("test-id");
  }
}