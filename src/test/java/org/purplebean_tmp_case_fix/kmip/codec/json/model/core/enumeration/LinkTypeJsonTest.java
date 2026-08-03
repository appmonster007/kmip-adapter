package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.LinkType;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LinkType JSON Serialization")
class LinkTypeJsonTest extends AbstractJsonSerializationTestSuite<LinkType> {
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
