package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateSplitKeyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateSplitKeyOpRequestPayload Ttlv Serialization Tests")
class CreateSplitKeyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CreateSplitKeyOpRequestPayload> {

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
