package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DiscoverVersionsOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DiscoverVersionsOpResponsePayload Xml Serialization Tests")
class DiscoverVersionsOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<DiscoverVersionsOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DiscoverVersionsOpResponsePayload> type() {
    return DiscoverVersionsOpResponsePayload.class;
  }

  @Override
  public DiscoverVersionsOpResponsePayload createDefault() {
    return DiscoverVersionsOpResponsePayload
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .build();
  }

  @Override
  public DiscoverVersionsOpResponsePayload createVariant() {
    return DiscoverVersionsOpResponsePayload
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
        .build();
  }
}
