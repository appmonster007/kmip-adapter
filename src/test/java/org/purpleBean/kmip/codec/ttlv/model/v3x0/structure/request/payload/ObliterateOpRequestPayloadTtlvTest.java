package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.request.payload.ObliterateOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObliterateOpRequestPayload Ttlv Serialization Tests")
class ObliterateOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ObliterateOpRequestPayload> {

  @Override
  public Class<ObliterateOpRequestPayload> type() {
    return ObliterateOpRequestPayload.class;
  }

  @Override
  public ObliterateOpRequestPayload createDefault() {
    return ObliterateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public ObliterateOpRequestPayload createVariant() {
    return ObliterateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}