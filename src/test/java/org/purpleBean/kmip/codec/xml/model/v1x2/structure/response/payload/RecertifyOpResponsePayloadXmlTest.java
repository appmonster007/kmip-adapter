package org.purpleBean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.RecertifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RecertifyOpResponsePayload Xml Serialization Tests")
class RecertifyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RecertifyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<RecertifyOpResponsePayload> type() {
    return RecertifyOpResponsePayload.class;
  }

  @Override
  public RecertifyOpResponsePayload createDefault() {
    return RecertifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }

  @Override
  public RecertifyOpResponsePayload createVariant() {
    return RecertifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .build();
  }
}