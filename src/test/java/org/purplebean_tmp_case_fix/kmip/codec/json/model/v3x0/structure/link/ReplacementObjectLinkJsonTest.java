package org.purplebean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.ReplacementObjectLink;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ReplacementObjectLink Json Serialization Tests")
class ReplacementObjectLinkJsonTest
    extends AbstractJsonSerializationTestSuite<ReplacementObjectLink> {

  @Override
  public Class<ReplacementObjectLink> type() {
    return ReplacementObjectLink.class;
  }

  @Override
  public ReplacementObjectLink createDefault() {
    return ReplacementObjectLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public ReplacementObjectLink createVariant() {
    return ReplacementObjectLink.of(UniqueIdentifier.of("test-id"));
  }
}