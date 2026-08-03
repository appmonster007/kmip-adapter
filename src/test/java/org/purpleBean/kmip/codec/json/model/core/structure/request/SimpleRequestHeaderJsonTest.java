package org.purpleBean.kmip.codec.json.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SimpleRequestHeader JSON Serialization")
class SimpleRequestHeaderJsonTest extends AbstractJsonSerializationTestSuite<SimpleRequestHeader> {

  @Override
  public Class<SimpleRequestHeader> type() {
    return SimpleRequestHeader.class;
  }

  @Override
  public SimpleRequestHeader createDefault() {
    return SimpleRequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(1, 2))
        .build();
  }

  @Override
  public SimpleRequestHeader createVariant() {
    return SimpleRequestHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 0))
        .build();
  }

  @Override
  public boolean unsupportedSpecShouldFailSerialize() {
    return false; // model supports UnsupportedVersion
  }
}
