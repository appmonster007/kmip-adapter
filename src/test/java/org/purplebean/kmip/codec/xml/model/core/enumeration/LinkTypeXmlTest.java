package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.LinkType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LinkType XML Serialization")
class LinkTypeXmlTest extends AbstractXmlSerializationTestSuite<LinkType> {
  @Override
  public Class<LinkType> type() {
    return LinkType.class;
  }

  @Override
  public LinkType createDefault() {
    return LinkType.Standard.CERTIFICATE_LINK.inst();
  }

  @Override
  public LinkType createVariant() {
    return LinkType.Standard.PUBLIC_KEY_LINK.inst();
  }
}
