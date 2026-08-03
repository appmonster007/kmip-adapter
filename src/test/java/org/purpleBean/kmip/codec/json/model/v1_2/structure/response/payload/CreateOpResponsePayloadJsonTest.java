package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CreateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateOpResponsePayload Json Serialization Tests")
class CreateOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<CreateOpResponsePayload> type() {
    return CreateOpResponsePayload.class;
  }

  @Override
  public CreateOpResponsePayload createDefault() {
    return CreateOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
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
  public CreateOpResponsePayload createVariant() {
    return CreateOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.PUBLIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .build();
  }
}