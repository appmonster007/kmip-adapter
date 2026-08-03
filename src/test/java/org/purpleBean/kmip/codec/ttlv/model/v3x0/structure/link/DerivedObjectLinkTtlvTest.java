package org.purpleBean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.DerivedObjectLink;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DerivedObjectLink Ttlv Serialization Tests")
class DerivedObjectLinkTtlvTest extends AbstractTtlvSerializationTestSuite<DerivedObjectLink> {

  @Override
  public Class<DerivedObjectLink> type() {
    return DerivedObjectLink.class;
  }

  @Override
  public DerivedObjectLink createDefault() {
    return DerivedObjectLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public DerivedObjectLink createVariant() {
    return DerivedObjectLink.of(UniqueIdentifier.of("test-id"));
  }
}