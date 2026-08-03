package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.JoinSplitKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("JoinSplitKeyOpRequestPayload Xml Serialization Tests")
class JoinSplitKeyOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<JoinSplitKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<JoinSplitKeyOpRequestPayload> type() {
    return JoinSplitKeyOpRequestPayload.class;
  }

  @Override
  public JoinSplitKeyOpRequestPayload createDefault() {
    return JoinSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("part-1")
            .build())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("part-2")
            .build())
        .build();
  }

  @Override
  public JoinSplitKeyOpRequestPayload createVariant() {
    return JoinSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("part-3")
            .build())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("part-4")
            .build())
        .build();
  }
}