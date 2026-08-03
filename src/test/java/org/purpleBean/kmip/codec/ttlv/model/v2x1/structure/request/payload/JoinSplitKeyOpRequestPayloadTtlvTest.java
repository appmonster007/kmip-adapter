package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.request.payload.JoinSplitKeyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("JoinSplitKeyOpRequestPayload Ttlv Serialization Tests")
class JoinSplitKeyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<JoinSplitKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purplebean.kmip.api.KmipSpec.V2_1;
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