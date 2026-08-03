package org.purplebean.kmip.codec.json.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RngRetrieveOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RngRetrieveOpRequestPayload Json Serialization Tests")
class RngRetrieveOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<RngRetrieveOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RngRetrieveOpRequestPayload> type() {
    return RngRetrieveOpRequestPayload.class;
  }

  @Override
  public RngRetrieveOpRequestPayload createDefault() {
    return RngRetrieveOpRequestPayload
        .builder()
        .dataLength(DataLength.of(16))
        .build();
  }

  @Override
  public RngRetrieveOpRequestPayload createVariant() {
    return RngRetrieveOpRequestPayload
        .builder()
        .dataLength(DataLength.of(32))
        .build();
  }
}
