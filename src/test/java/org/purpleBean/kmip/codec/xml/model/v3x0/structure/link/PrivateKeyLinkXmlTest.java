package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PrivateKeyLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PrivateKeyLink Xml Serialization Tests")
class PrivateKeyLinkXmlTest extends AbstractXmlSerializationTestSuite<PrivateKeyLink> {

  @Override
  public Class<PrivateKeyLink> type() {
    return PrivateKeyLink.class;
  }

  @Override
  public PrivateKeyLink createDefault() {
    return PrivateKeyLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public PrivateKeyLink createVariant() {
    return PrivateKeyLink.of(UniqueIdentifier.of("test-id"));
  }
}