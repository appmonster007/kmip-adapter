package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DelegatedLoginOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.RequestCount;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DelegatedLoginOpRequestPayload Json Serialization Tests")
class DelegatedLoginOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<DelegatedLoginOpRequestPayload> {

  @Override
  public Class<DelegatedLoginOpRequestPayload> type() {
    return DelegatedLoginOpRequestPayload.class;
  }

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public DelegatedLoginOpRequestPayload createDefault() {
    return DelegatedLoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(3600))
        .requestCount(RequestCount.of(10))
        .build();
  }

  @Override
  public DelegatedLoginOpRequestPayload createVariant() {
    return DelegatedLoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(7200))
        .requestCount(RequestCount.of(20))
        .build();
  }
}