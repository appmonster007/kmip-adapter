package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.NotifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NotifyOpResponsePayload Xml Serialization Tests")
class NotifyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<NotifyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<NotifyOpResponsePayload> type() {
    return NotifyOpResponsePayload.class;
  }

  @Override
  public NotifyOpResponsePayload createDefault() {
    return NotifyOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public NotifyOpResponsePayload createVariant() {
    return NotifyOpResponsePayload
        .builder()
        .build();
  }
}
