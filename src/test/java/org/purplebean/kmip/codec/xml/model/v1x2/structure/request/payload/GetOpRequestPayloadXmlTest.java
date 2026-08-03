package org.purplebean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GetOpRequestPayload Xml Serialization Tests")
class GetOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<GetOpRequestPayload> {

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