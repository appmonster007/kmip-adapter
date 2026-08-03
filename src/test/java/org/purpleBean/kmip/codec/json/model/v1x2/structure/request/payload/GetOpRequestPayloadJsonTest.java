package org.purpleBean.kmip.codec.json.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.GetOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetOpRequestPayload Json Serialization Tests")
class GetOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<GetOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<GetOpRequestPayload> type() {
    return GetOpRequestPayload.class;
  }

  @Override
  public GetOpRequestPayload createDefault() {
    return GetOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public GetOpRequestPayload createVariant() {
    return GetOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid-variant"))
        .build();
  }
}