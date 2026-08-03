package org.purpleBean.kmip.codec.json.model.v3_0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.link.ReplacementObjectLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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