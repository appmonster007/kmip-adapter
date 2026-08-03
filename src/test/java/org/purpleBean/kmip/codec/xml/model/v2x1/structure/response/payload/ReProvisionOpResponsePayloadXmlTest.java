package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ReProvisionOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReProvisionOpResponsePayload Xml Serialization Tests")
class ReProvisionOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ReProvisionOpResponsePayload> {

  @Override
  public Class<ReProvisionOpResponsePayload> type() {
    return ReProvisionOpResponsePayload.class;
  }

  @Override
  public ReProvisionOpResponsePayload createDefault() {
    return ReProvisionOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("reprovision-uid-1")
            .build())
        .build();
  }

  @Override
  public ReProvisionOpResponsePayload createVariant() {
    return ReProvisionOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("reprovision-uid-2")
            .build())
        .build();
  }
}
