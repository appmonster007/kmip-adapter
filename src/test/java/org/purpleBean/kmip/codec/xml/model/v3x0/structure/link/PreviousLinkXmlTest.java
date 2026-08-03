package org.purplebean.kmip.codec.xml.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.PreviousLink;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PreviousLink Xml Serialization Tests")
class PreviousLinkXmlTest extends AbstractXmlSerializationTestSuite<PreviousLink> {

  @Override
  public Class<PreviousLink> type() {
    return PreviousLink.class;
  }

  @Override
  public PreviousLink createDefault() {
    return PreviousLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public PreviousLink createVariant() {
    return PreviousLink.of(UniqueIdentifier.of("test-id"));
  }
}