package org.purplebean.kmip.codec.xml.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleRequestHeader XML Serialization")
class SimpleRequestHeaderXmlTest extends AbstractXmlSerializationTestSuite<SimpleRequestHeader> {

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
