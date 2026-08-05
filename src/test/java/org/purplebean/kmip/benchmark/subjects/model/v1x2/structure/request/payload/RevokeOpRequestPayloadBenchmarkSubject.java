package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RevokeOpRequestPayload;

/**
 * Benchmark subject for {@link RevokeOpRequestPayload}.
 */
public class RevokeOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RevokeOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link RevokeOpRequestPayloadBenchmarkSubject}.
   */
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
