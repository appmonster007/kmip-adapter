package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.GetAttributesOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetAttributesOpResponsePayload Json Serialization Tests")
class GetAttributesOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<GetAttributesOpResponsePayload> {

  @Override
  public Class<GetAttributesOpResponsePayload> type() {
    return GetAttributesOpResponsePayload.class;
  }

  @Override
  public GetAttributesOpResponsePayload createDefault() {
    return GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributes(Attributes.of(List.of()))
        .build();
  }

  @Override
  public GetAttributesOpResponsePayload createVariant() {
    return GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributes(Attributes.of(List.of()))
        .build();
  }
}
