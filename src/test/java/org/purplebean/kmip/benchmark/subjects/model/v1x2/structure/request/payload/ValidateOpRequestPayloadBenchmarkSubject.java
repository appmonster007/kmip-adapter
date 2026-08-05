package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.ValidityDate;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ValidateOpRequestPayload;

/**
 * Benchmark subject for {@link ValidateOpRequestPayload}.
 */
public class ValidateOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ValidateOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ValidateOpRequestPayloadBenchmarkSubject}.
   */
  public ValidateOpRequestPayloadBenchmarkSubject() throws Exception {
    ValidateOpRequestPayload subject = ValidateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .validityDate(ValidityDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .build();
    initialize(subject, ValidateOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ValidateOpRequestPayload";
  }
}
