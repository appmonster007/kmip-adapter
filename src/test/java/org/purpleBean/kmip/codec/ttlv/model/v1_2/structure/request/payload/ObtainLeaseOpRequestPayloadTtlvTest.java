package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ObtainLeaseOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ObtainLeaseOpRequestPayload Ttlv Serialization Tests")
class ObtainLeaseOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ObtainLeaseOpRequestPayload> {

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
