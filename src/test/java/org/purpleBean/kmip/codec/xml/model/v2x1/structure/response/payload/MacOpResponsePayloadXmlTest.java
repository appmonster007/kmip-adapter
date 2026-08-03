package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.MacOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MacOpResponsePayload Xml Serialization Tests")
class MacOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<MacOpResponsePayload> {

  @Override
  public Class<MacOpResponsePayload> type() {
    return MacOpResponsePayload.class;
  }

  @Override
  public MacOpResponsePayload createDefault() {
    return MacOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public MacOpResponsePayload createVariant() {
    return MacOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}