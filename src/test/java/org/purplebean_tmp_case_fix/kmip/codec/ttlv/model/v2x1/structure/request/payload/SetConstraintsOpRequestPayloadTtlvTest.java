package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Constraints;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetConstraintsOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetConstraintsOpRequestPayload Ttlv Serialization Tests")
class SetConstraintsOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetConstraintsOpRequestPayload> {

  @Override
  public Class<SetConstraintsOpRequestPayload> type() {
    return SetConstraintsOpRequestPayload.class;
  }

  @Override
  public SetConstraintsOpRequestPayload createDefault() {
    return SetConstraintsOpRequestPayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }

  @Override
  public SetConstraintsOpRequestPayload createVariant() {
    return SetConstraintsOpRequestPayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }
}