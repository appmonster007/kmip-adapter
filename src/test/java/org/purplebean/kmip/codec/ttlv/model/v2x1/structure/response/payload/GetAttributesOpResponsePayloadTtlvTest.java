package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.Attributes;
import org.purplebean.kmip.model.v2x1.structure.response.payload.GetAttributesOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetAttributesOpResponsePayload Ttlv Serialization Tests")
class GetAttributesOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<GetAttributesOpResponsePayload> {

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
