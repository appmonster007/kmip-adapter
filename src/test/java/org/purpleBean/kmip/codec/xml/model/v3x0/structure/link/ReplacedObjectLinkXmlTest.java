package org.purpleBean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.ReplacedObjectLink;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReplacedObjectLink Xml Serialization Tests")
class ReplacedObjectLinkXmlTest extends AbstractXmlSerializationTestSuite<ReplacedObjectLink> {

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