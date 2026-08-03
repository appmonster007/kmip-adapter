package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.JoinSplitKeyOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("JoinSplitKeyOpResponsePayload Xml Serialization Tests")
class JoinSplitKeyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<JoinSplitKeyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<JoinSplitKeyOpResponsePayload> type() {
    return JoinSplitKeyOpResponsePayload.class;
  }

  @Override
  public JoinSplitKeyOpResponsePayload createDefault() {
    return JoinSplitKeyOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public JoinSplitKeyOpResponsePayload createVariant() {
    return JoinSplitKeyOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
