package org.purpleBean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.ReKeyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReKeyOpResponsePayload Xml Serialization Tests")
class ReKeyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ReKeyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<ReKeyOpResponsePayload> type() {
    return ReKeyOpResponsePayload.class;
  }

  @Override
  public ReKeyOpResponsePayload createDefault() {
    return ReKeyOpResponsePayload
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
  public ReKeyOpResponsePayload createVariant() {
    return ReKeyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .build();
  }
}