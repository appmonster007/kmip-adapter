package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.RevokeOpRequestPayload;

public class RevokeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RevokeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public RevokeOpRequestPayloadBenchmarkSubject() throws Exception {
    RevokeOpRequestPayload subject = RevokeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .revocationReason(
            RevocationReason
                .builder()
                .revocationReasonCode(RevocationReasonCode.Standard.AFFILIATION_CHANGED.inst())
                .build()
        )
        .compromiseOccurrenceDate(CompromiseOccurrenceDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .build();
    initialize(subject, RevokeOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "RevokeOpRequestPayload";
  }
}
