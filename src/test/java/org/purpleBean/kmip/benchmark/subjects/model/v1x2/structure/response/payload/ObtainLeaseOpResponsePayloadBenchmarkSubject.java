package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.LastChangeDate;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ObtainLeaseOpResponsePayload;

public class ObtainLeaseOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ObtainLeaseOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public ObtainLeaseOpResponsePayloadBenchmarkSubject() throws Exception {
    ObtainLeaseOpResponsePayload subject = ObtainLeaseOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .leaseTime(LeaseTime.of(3600))
        .lastChangeDate(LastChangeDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .build();
    initialize(subject, ObtainLeaseOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ObtainLeaseOpResponsePayload";
  }
}
