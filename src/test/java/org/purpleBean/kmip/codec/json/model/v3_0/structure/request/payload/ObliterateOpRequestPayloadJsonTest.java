package org.purpleBean.kmip.codec.json.model.v3_0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.ObliterateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ObliterateOpRequestPayload Json Serialization Tests")
class ObliterateOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ObliterateOpRequestPayload> {

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