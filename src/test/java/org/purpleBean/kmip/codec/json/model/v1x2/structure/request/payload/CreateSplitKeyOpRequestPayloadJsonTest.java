package org.purpleBean.kmip.codec.json.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.CreateSplitKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateSplitKeyOpRequestPayload Json Serialization Tests")
class CreateSplitKeyOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateSplitKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CreateSplitKeyOpRequestPayload> type() {
    return CreateSplitKeyOpRequestPayload.class;
  }

  @Override
  public CreateSplitKeyOpRequestPayload createDefault() {
    return CreateSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .splitKeyParts(SplitKeyParts.of(3))
        .splitKeyThreshold(SplitKeyThreshold.of(2))
        .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }

  @Override
  public CreateSplitKeyOpRequestPayload createVariant() {
    return CreateSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
        .splitKeyParts(SplitKeyParts.of(5))
        .splitKeyThreshold(SplitKeyThreshold.of(3))
        .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.POLYNOMIAL_SHARING_GF_28))
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }
}
