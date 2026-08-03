package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.LastChangeDate;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ObtainLeaseOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObtainLeaseOpResponsePayload Xml Serialization Tests")
class ObtainLeaseOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ObtainLeaseOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ObtainLeaseOpResponsePayload> type() {
    return ObtainLeaseOpResponsePayload.class;
  }

  @Override
  public ObtainLeaseOpResponsePayload createDefault() {
    return ObtainLeaseOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .leaseTime(LeaseTime.of(3600))
        .lastChangeDate(LastChangeDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .build();
  }

  @Override
  public ObtainLeaseOpResponsePayload createVariant() {
    return ObtainLeaseOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .leaseTime(LeaseTime.of(7200))
        .lastChangeDate(LastChangeDate.of(OffsetDateTime
            .now(ZoneOffset.UTC)
            .plusDays(1)))
        .build();
  }
}
