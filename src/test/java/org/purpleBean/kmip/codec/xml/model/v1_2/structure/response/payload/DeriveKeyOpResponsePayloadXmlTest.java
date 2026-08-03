package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeriveKeyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeriveKeyOpResponsePayload Xml Serialization Tests")
class DeriveKeyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<DeriveKeyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<DeriveKeyOpResponsePayload> type() {
    return DeriveKeyOpResponsePayload.class;
  }

  @Override
  public DeriveKeyOpResponsePayload createDefault() {
    return DeriveKeyOpResponsePayload
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
  public DeriveKeyOpResponsePayload createVariant() {
    return DeriveKeyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .build();
  }
}
