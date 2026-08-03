package org.purplebean.kmip.codec.json.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ObtainLeaseOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObtainLeaseOpRequestPayload Json Serialization Tests")
class ObtainLeaseOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ObtainLeaseOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ObtainLeaseOpRequestPayload> type() {
    return ObtainLeaseOpRequestPayload.class;
  }

  @Override
  public ObtainLeaseOpRequestPayload createDefault() {
    return ObtainLeaseOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public ObtainLeaseOpRequestPayload createVariant() {
    return ObtainLeaseOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
