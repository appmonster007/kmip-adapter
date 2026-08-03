package org.purplebean.kmip.model.core.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ProtocolVersion Structure Semantics")
class ProtocolVersionTest extends AbstractKmipStructureTestSuite<ProtocolVersion> {

  @Override
  protected Class<ProtocolVersion> type() {
    return ProtocolVersion.class;
  }

  @Override
  protected ProtocolVersion createDefault() {
    return ProtocolVersion.of(1, 2);
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // ProtocolVersion exposes major, minor as two components
    // No deep asserts here to keep this reusable; specific tests can subclass and add more
  }

  @Override
  protected boolean expectedSupportedForUnsupportedSpec() {
    return true;
  }
}
