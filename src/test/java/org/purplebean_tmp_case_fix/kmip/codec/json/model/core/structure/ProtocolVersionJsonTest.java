package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProtocolVersion JSON Serialization")
class ProtocolVersionJsonTest extends AbstractJsonSerializationTestSuite<ProtocolVersion> {

  @Override
  public Class<ProtocolVersion> type() {
    return ProtocolVersion.class;
  }

  @Override
  public ProtocolVersion createDefault() {
    return ProtocolVersion.of(1, 2);
  }

  @Override
  public ProtocolVersion createVariant() {
    return ProtocolVersion.of(2, 0);
  }

  @Override
  public boolean unsupportedSpecShouldFailSerialize() {
    return false;
  }
}
