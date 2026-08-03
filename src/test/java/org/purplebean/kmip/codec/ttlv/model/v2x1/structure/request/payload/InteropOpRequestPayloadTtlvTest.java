package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purplebean.kmip.model.v2x1.structure.request.payload.InteropOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InteropOpRequestPayload Ttlv Serialization Tests")
class InteropOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<InteropOpRequestPayload> {

  @Override
  public Class<InteropOpRequestPayload> type() {
    return InteropOpRequestPayload.class;
  }

  @Override
  public InteropOpRequestPayload createDefault() {
    return InteropOpRequestPayload
        .builder()
        .interopFunction(InteropFunction.Standard.BEGIN.inst())
        .build();
  }

  @Override
  public InteropOpRequestPayload createVariant() {
    return InteropOpRequestPayload
        .builder()
        .interopFunction(InteropFunction.Standard.BEGIN.inst())
        .build();
  }
}