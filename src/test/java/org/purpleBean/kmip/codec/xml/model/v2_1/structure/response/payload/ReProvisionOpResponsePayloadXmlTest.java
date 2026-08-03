package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ReProvisionOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
