package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.LinkType;
import org.purplebean.kmip.model.core.structure.Link;
import org.purplebean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Link Json Serialization Tests")
class LinkJsonTest extends AbstractJsonSerializationTestSuite<Link> {

  @Override
  public Class<Link> type() {
    return Link.class;
  }

  @Override
  public Link createDefault() {
    return Link
        .builder()
        .linkType(LinkType.Standard.CERTIFICATE_LINK.inst())
        .linkedObjectIdentifier(LinkedObjectIdentifier.of("test-id"))
        .build();
  }
}