package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.LastChangeDate;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ObtainLeaseOpResponsePayload;

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
