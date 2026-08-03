package org.purplebean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CreateSplitKeyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateSplitKeyOpRequestPayload Xml Serialization Tests")
class CreateSplitKeyOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CreateSplitKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purplebean.kmip.api.KmipSpec.V2_1;
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
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("source-key-id")
            .build())
        .splitKeyParts(SplitKeyParts.of(3))
        .splitKeyThreshold(SplitKeyThreshold.of(2))
        .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
        .build();
  }

  @Override
  public CreateSplitKeyOpRequestPayload createVariant() {
    return CreateSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("source-key-id-2")
            .build())
        .splitKeyParts(SplitKeyParts.of(5))
        .splitKeyThreshold(SplitKeyThreshold.of(3))
        .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.POLYNOMIAL_SHARING_GF_28))
        .build();
  }
}