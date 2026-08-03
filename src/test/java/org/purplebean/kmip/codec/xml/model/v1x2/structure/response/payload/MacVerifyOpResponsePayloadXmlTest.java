package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.MacVerifyOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MacVerifyOpResponsePayload Xml Serialization Tests")
class MacVerifyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<MacVerifyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<MacVerifyOpResponsePayload> type() {
    return MacVerifyOpResponsePayload.class;
  }

  @Override
  public MacVerifyOpResponsePayload createDefault() {
    return MacVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
        .build();
  }

  @Override
  public MacVerifyOpResponsePayload createVariant() {
    return MacVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.INVALID))
        .build();
  }
}
